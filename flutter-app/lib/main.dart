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
  late Future<List<MovieData>> movieData;

  Future<List<MovieData>> fetchMovie() async {
    final response = await http
        .get(Uri.parse('http://localhost:8080/recommendation/movie/sofie'));

    if (response.statusCode == 200) {
      // If the server did return a 200 OK response,
      // then parse the JSON.
      List<dynamic> responseData = jsonDecode(response.body);
      return responseData.map((e) => MovieData.fromJson(e)).toList();
    } else {
      // If the server did not return a 200 OK response,
      // then throw an exception.
      print(response.statusCode);
      throw Exception('Failed to load data');
    }
  }

  @override
  void initState() {
    super.initState();
    movieData = fetchMovie();
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
        appBar: AppBar(
          title: Text(widget.title),
        ),
        body: Column(
          children: [
            FutureBuilder<List<MovieData>>(
              future: movieData,
              builder: (context, snapshot) {
                if (snapshot.hasData) {
                  return Expanded(
                      child: MovieCard(movieData: snapshot.data!.first));
                } else if (snapshot.hasError) {
                  return Text('${snapshot.error}');
                }

                // By default, show a loading spinner.
                return const CircularProgressIndicator();
              },
            ),
            ActionButtonsWidget()
          ],
        ));
  }
}
