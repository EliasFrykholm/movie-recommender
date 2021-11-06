import 'package:app/action_buttons_widget.dart';
import 'package:app/api/rate.dart';
import 'package:app/api/recommendations.dart';
import 'package:app/exceptions/unknown_user_exception.dart';
import 'package:app/genre_rating.dart';
import 'package:app/models/movie_data.dart';
import 'package:flutter/material.dart';

import 'MovieCard/movie_card.dart';

void main() async {
  runApp(MyApp());
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
        home: MyHomePage(),
        routes: <String, WidgetBuilder>{
          '/genres': (BuildContext context) => GenreRating(),
        });
  }
}

class MyHomePage extends StatefulWidget {
  const MyHomePage({Key? key}) : super(key: key);

  @override
  State<MyHomePage> createState() => _MyHomePageState();
}

class _MyHomePageState extends State<MyHomePage> {
  List<MovieData> _movieData = [];

  @override
  void initState() {
    super.initState();
    fetchMovies()
        .then((value) => {
              setState(() {
                _movieData = value;
              })
            })
        .onError((error, stackTrace) => {
              if (error.runtimeType == UnknownUserException)
                Navigator.pushReplacementNamed(context, "/genres")
              else
                throw error.runtimeType
            });
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text("MovieFinder"),
      ),
      body: Column(
        children: [
          Expanded(
              child: Stack(
                  fit: StackFit.expand,
                  clipBehavior: Clip.none,
                  children: [
                    Container(),
                    if (_movieData.isEmpty)
                      const Center(child: CircularProgressIndicator())
                    else
                      ..._movieData.take(2).map((movie) => Container(
                          margin: _movieData.first == movie
                              ? const EdgeInsets.all(15)
                              : const EdgeInsets.only(
                                  left: 5, right: 25, top: 5, bottom: 25),
                          child: Dismissible(
                              key: UniqueKey(),
                              onDismissed: (direction) {
                                if (direction == DismissDirection.startToEnd) {
                                  rateMovie(true, movie.tmdbId);
                                } else {
                                  rateMovie(true, movie.tmdbId);
                                }
                                setState(() {
                                  _movieData.remove(movie);
                                });
                                if (_movieData.length <= 2) {
                                  fetchMovies();
                                }
                              },
                              child: MovieCard(movieData: movie))))

                    // By default, show a loading spinner.
                  ].reversed.toList())),
          const Align(
              alignment: Alignment.bottomCenter, child: ActionButtonsWidget())
        ],
      ),
    );
  }
}
