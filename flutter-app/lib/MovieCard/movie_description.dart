import 'package:app/MovieCard/genre_badge.dart';
import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';

class MovieDescription extends StatelessWidget {
  const MovieDescription(
      {Key? key,
      this.description,
      this.releaseDate,
      this.genres,
      this.rating,
      this.runtime})
      : super(key: key);

  final String? description;
  final DateTime? releaseDate;
  final List<String>? genres;
  final double? rating;
  final int? runtime;

  @override
  Widget build(BuildContext context) {
    return Container(
        padding: const EdgeInsets.all(10),
        color: Colors.black87,
        child: Column(mainAxisSize: MainAxisSize.min, children: [
          if (rating != null)
            Row(
                mainAxisAlignment: MainAxisAlignment.spaceBetween,
                crossAxisAlignment: CrossAxisAlignment.start,
                children: [
                  Column(
                      crossAxisAlignment: CrossAxisAlignment.start,
                      children: [
                        if (genres != null)
                          Row(
                              children: genres!
                                  .map((e) => GenreBadge(genre: e))
                                  .toList()),
                        if (releaseDate != null)
                          Text(
                              "${releaseDate!.year}-${releaseDate!.month}-${releaseDate!.day}"),
                        Text("$runtime min")
                      ]),
                  Stack(children: [
                    CircularProgressIndicator(
                      value: rating! / 10,
                    ),
                    Positioned.fill(
                        child: Align(
                            alignment: Alignment.center,
                            child: Text(rating!.toString()))),
                  ])
                ]),
          const Divider(
            height: 20,
            thickness: 3,
            indent: 20,
            endIndent: 20,
          ),
          if (description != null) Text(description!)
        ]));
  }
}
