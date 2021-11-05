import 'package:app/MovieCard/expanded_section.dart';
import 'package:app/MovieCard/movie_description.dart';
import 'package:app/models/movie_data.dart';
import 'package:flutter/material.dart';
import 'package:url_launcher/url_launcher.dart';

class MovieCardContent extends StatefulWidget {
  const MovieCardContent({Key? key, required this.movieData}) : super(key: key);
  final MovieData movieData;

  @override
  State<MovieCardContent> createState() => _MovieCardContentState();
}

class _MovieCardContentState extends State<MovieCardContent> {
  bool _expandedDescription = false;

  void _launchTrailer(String url) async {
    await canLaunch(url) ? await launch(url) : throw 'Could not launch $url';
  }

  void _toggleExpandedDescription() {
    setState(() {
      _expandedDescription = !_expandedDescription;
    });
  }

  @override
  Widget build(BuildContext context) {
    return Column(
      children: [
        Container(
            padding: const EdgeInsets.all(5),
            color: Colors.black,
            child: Row(children: [
              FittedBox(
                  fit: BoxFit.fitWidth,
                  child: Text(
                    "${widget.movieData.title} ${widget.movieData.releaseDate.year}",
                  )),
              IconButton(
                  onPressed: () =>
                      {_launchTrailer(widget.movieData.trailerUrl)},
                  icon: const Icon(Icons.smart_display)),
            ])),
        Expanded(
            child: InkWell(
                onTap: _toggleExpandedDescription,
                child: Container(
                    decoration: BoxDecoration(
                      image: DecorationImage(
                          image: NetworkImage(widget.movieData.coverArtUrl),
                          fit: BoxFit.cover),
                    ),
                    alignment: Alignment.bottomCenter,
                    child: ExpandedSection(
                        expand: _expandedDescription,
                        child: MovieDescription(
                          rating: widget.movieData.rating,
                          releaseDate: widget.movieData.releaseDate,
                          runtime: widget.movieData.runtime,
                          description: widget.movieData.description,
                          genres: widget.movieData.genres,
                        )))))
      ],
    );
  }
}
