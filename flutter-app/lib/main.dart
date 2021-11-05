import 'dart:convert';

import 'package:app/action_buttons_widget.dart';
import 'package:app/models/movie_data.dart';
import 'package:flutter/material.dart';
import 'package:http/http.dart' as http;

import 'MovieCard/movie_card.dart';

void main() {
  runApp(const MyApp());
}

class MyApp extends StatelessWidget {
  const MyApp({Key? key}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: 'Flutter Demo',
      theme: ThemeData(
          primaryColor: Colors.deepOrange,
          primarySwatch: Colors.deepOrange,
          brightness: Brightness.dark),
      home: const MyHomePage(title: 'Movie recommender'),
    );
  }
}

class MyHomePage extends StatefulWidget {
  const MyHomePage({Key? key, required this.title}) : super(key: key);

  final String title;

  @override
  State<MyHomePage> createState() => _MyHomePageState();
}

class _MyHomePageState extends State<MyHomePage> {
  List<MovieData> _movieData = [];

  void fetchMovies() async {
    final response = await http
        .get(Uri.parse('http://localhost:8080/recommendation/movie/elias'));

    if (response.statusCode == 200) {
      // If the server did return a 200 OK response,
      // then parse the JSON.
      List<dynamic> responseData = jsonDecode(response.body);
      List<MovieData> movies =
          responseData.map((e) => MovieData.fromJson(e)).toList();
      setState(() {
        _movieData.addAll(movies);
      });
    } else {
      // If the server did not return a 200 OK response,
      // then throw an exception.
      print(response.statusCode);
      throw Exception('Failed to load data');
    }
  }

  void _rateMovie(bool liked, int tmdbId) async {
    final response = await http.post(
        Uri.parse("http://localhost:8080/rate/movie"),
        headers: <String, String>{
          'Content-Type': 'application/json; charset=UTF-8',
        },
        body:
            json.encode({"userId": "elias", "tmdbId": tmdbId, "liked": liked}));

    if (response.statusCode == 200) {
    } else {
      // If the server did not return a 200 OK response,
      // then throw an exception.
      print(response.statusCode);
    }
  }

  @override
  void initState() {
    super.initState();
    fetchMovies();
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text(widget.title),
      ),
      body: Column(
        children: [
          Expanded(
              child: Stack(children: [
            if (_movieData.isEmpty)
              const CircularProgressIndicator()
            else
              ..._movieData.map((movie) => Dismissible(
                  key: UniqueKey(),
                  onDismissed: (direction) {
                    if (direction == DismissDirection.startToEnd) {
                      _rateMovie(true, movie.tmdbId);
                    } else {
                      _rateMovie(true, movie.tmdbId);
                    }
                    setState(() {
                      _movieData.remove(movie);
                    });
                    if (_movieData.length <= 2) {
                      fetchMovies();
                    }
                  },
                  child: MovieCard(movieData: movie)))

            // By default, show a loading spinner.
          ])),
          const Align(
              alignment: Alignment.bottomCenter, child: ActionButtonsWidget())
        ],
      ),
    );
  }
}
