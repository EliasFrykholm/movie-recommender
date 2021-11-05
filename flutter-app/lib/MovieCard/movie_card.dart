import 'package:app/MovieCard/movie_card_content.dart';
import 'package:app/models/movie_data.dart';
import 'package:flutter/material.dart';

class MovieCard extends StatelessWidget {
  const MovieCard({Key? key, required this.movieData}) : super(key: key);
  final MovieData movieData;

  @override
  Widget build(BuildContext context) {
    return Container(
      decoration: BoxDecoration(
          borderRadius: BorderRadius.circular(20),
          boxShadow: const [
            BoxShadow(
                color: Colors.black38,
                spreadRadius: 4,
                blurRadius: 1,
                offset: Offset(-4, 4)),
          ]),
      child: ClipRRect(
        borderRadius: BorderRadius.circular(20),
        child: MovieCardContent(
          movieData: movieData,
        ),
      ),
    );
  }
}
