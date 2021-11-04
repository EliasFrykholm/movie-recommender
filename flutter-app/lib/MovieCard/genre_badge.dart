import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';

class GenreBadge extends StatelessWidget {
  const GenreBadge({
    Key? key,
    required this.genre,
  }) : super(key: key);

  final String genre;

  @override
  Widget build(BuildContext context) {
    return Container(
      margin: const EdgeInsets.only(right: 5),
      padding: const EdgeInsets.all(3),
      decoration: BoxDecoration(
        border: Border.all(color: Colors.primaries.first, width: 1),
        borderRadius: BorderRadius.circular(6),
      ),
      constraints: const BoxConstraints(
        minWidth: 12,
        minHeight: 12,
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
