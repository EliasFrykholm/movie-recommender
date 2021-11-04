import 'package:app/movie_card_content.dart';
import 'package:flutter/material.dart';

class MovieCard extends StatefulWidget {
  const MovieCard({Key? key}) : super(key: key);

  @override
  State<MovieCard> createState() => _MovieCardState();
}

class _MovieCardState extends State<MovieCard> {
  @override
  Widget build(BuildContext context) {
    return Container(
      margin: const EdgeInsets.all(20),
      child: ClipRRect(
        borderRadius: BorderRadius.circular(20),
        child: const MovieCardContent(),
      ),
    );
  }
}
