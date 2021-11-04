import 'package:app/expanded_section.dart';
import 'package:flutter/material.dart';

class MovieCardContent extends StatefulWidget {
  const MovieCardContent({Key? key}) : super(key: key);

  @override
  State<MovieCardContent> createState() => _MovieCardContentState();
}

class _MovieCardContentState extends State<MovieCardContent> {
  bool _expandedDescription = false;
  String movie = "Movie title";
  String year = "2021";
  String description =
      "Investigative journalist Eddie Brock attempts a comeback following a scandal, but accidentally becomes the host of Venom, a violent, super powerful alien symbiote. Soon, he must rely on his newfound powers to protect the world from a shadowy organization looking for a symbiote of their own.";

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
            color: Colors.black87,
            child: Row(children: [
              Text(
                "$movie $year",
                style: const TextStyle(fontSize: 20),
              ),
              const Spacer(),
              IconButton(
                  onPressed: () => {}, icon: const Icon(Icons.smart_display)),
            ])),
        Expanded(
            child: InkWell(
                onTap: _toggleExpandedDescription,
                child: Container(
                    decoration: const BoxDecoration(
                      image: DecorationImage(
                          image: NetworkImage(
                              "https://image.tmdb.org/t/p/original/2uNW4WbgBXL25BAbXGLnLqX71Sw.jpg"),
                          fit: BoxFit.cover),
                    ),
                    alignment: Alignment.bottomCenter,
                    child: ExpandedSection(
                        expand: _expandedDescription,
                        child: Align(
                            alignment: Alignment.bottomCenter,
                            child: Container(
                                padding: const EdgeInsets.all(10),
                                color: Colors.black87,
                                child: Text(description)))))))
      ],
    );
  }
}
