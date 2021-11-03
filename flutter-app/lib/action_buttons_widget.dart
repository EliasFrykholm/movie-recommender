import 'package:flutter/material.dart';

class ActionButtonsWidget extends StatefulWidget {
  const ActionButtonsWidget({Key? key}) : super(key: key);

  @override
  State<ActionButtonsWidget> createState() => _ActionButtonsWidgetState();
}

class _ActionButtonsWidgetState extends State<ActionButtonsWidget> {
  bool _liked = false;

  void _toggleLiked() {
    setState(() {
      _liked = !_liked;
    });
  }

  @override
  Widget build(BuildContext context) {
    return Row(
      mainAxisAlignment: MainAxisAlignment.spaceAround,
      children: <Widget>[
        Ink(
          decoration: BoxDecoration(
            border: Border.all(color: Colors.red, width: 4.0),
            shape: BoxShape.circle,
          ),
          child: IconButton(
            iconSize: 50,
            icon: const Icon(Icons.clear),
            color: Colors.red,
            onPressed: () {},
          ),
        ),
        Ink(
          decoration: BoxDecoration(
            border: Border.all(color: Colors.yellow, width: 4.0),
            shape: BoxShape.circle,
          ),
          child: IconButton(
            iconSize: 40,
            icon: Icon(_liked ? Icons.star : Icons.star_border),
            color: Colors.yellow,
            onPressed: () {_toggleLiked();},
          ),
        ),
        Ink(
          decoration: BoxDecoration(
            border: Border.all(color: Colors.green, width: 4.0),
            shape: BoxShape.circle,
          ),
          child: IconButton(
            iconSize: 50,
            icon: const Icon(Icons.done),
            color: Colors.green,
            onPressed: () {},
          ),
        ),
      ]
    );
  }
}
