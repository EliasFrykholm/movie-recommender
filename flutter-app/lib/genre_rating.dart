import 'package:app/api/data.dart';
import 'package:app/api/rate.dart';
import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';

class GenreRating extends StatefulWidget {
  @override
  GenreRatingState createState() => new GenreRatingState();
}

class GenreRatingState extends State<GenreRating> {
  List<Genre> genres = [];

  void itemChange(bool val, int index) {
    setState(() {
      genres[index].liked = val;
    });
  }

  @override
  void initState() {
    super.initState();
    fetchGenres().then((value) => setState(() {
          genres = value;
        }));
  }

  @override
  Widget build(BuildContext context) {
    void onSave() {
      rateGenres(genres).then(
          (value) => {if (value) Navigator.popAndPushNamed(context, "/")});
    }

    return Scaffold(
      appBar: AppBar(
        title: const Text('Select preferred genres'),
      ),
      body: ListView.builder(
          itemCount: genres.length,
          itemBuilder: (BuildContext context, int index) {
            return Card(
              child: Column(
                children: <Widget>[
                  CheckboxListTile(
                      activeColor: Colors.primaries.first,
                      dense: true,
                      //font change
                      title: Text(
                        genres[index].name,
                        style: const TextStyle(
                            fontSize: 18,
                            fontWeight: FontWeight.w600,
                            letterSpacing: 0.5),
                      ),
                      value: genres[index].liked,
                      onChanged: (val) {
                        if (val != null) itemChange(val, index);
                      })
                ],
              ),
            );
          }),
      floatingActionButton: FloatingActionButton(
        heroTag: null,
        child: Icon(Icons.save),
        backgroundColor: Colors.primaries.first,
        foregroundColor: Colors.black,
        onPressed: onSave,
      ),
    );
  }
}

class Genre {
  Genre({required this.name, this.liked = false});

  final String name;
  bool liked;

  Map<String, dynamic> toJson() => {
        'genre': name,
        'liked': liked,
      };
}
