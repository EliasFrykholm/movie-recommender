import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';

class GenreBadge extends StatelessWidget {
  const GenreBadge({
    Key? key,
    required this.genre,
    this.selected = false,
  }) : super(key: key);

  final String genre;
  final bool selected;

  @override
  Widget build(BuildContext context) {
    return Container(
      margin: const EdgeInsets.only(right: 5),
      padding: const EdgeInsets.all(3),
      decoration: BoxDecoration(
        color: selected ? Colors.primaries.first : null,
        border: Border.all(color: Colors.primaries.first, width: 1),
        borderRadius: BorderRadius.circular(6),
      ),
      child: Text(
        genre,
        style: const TextStyle(
          fontSize: 8,
        ),
        textAlign: TextAlign.center,
      ),
    );
  }
}
