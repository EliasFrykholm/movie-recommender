import 'dart:convert';

import 'package:app/main.dart';
import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:http/http.dart' as http;

class GenreRating extends StatefulWidget {
  @override
  GenreRatingState createState() => new GenreRatingState();
}

class GenreRatingState extends State<GenreRating> {
  List<Genre> genres = [];

  void fetchGenres() async {
    final response =
        await http.get(Uri.parse("http://localhost:8081/moviedata/genres"));
    if (response.statusCode == 200) {
      setState(() {
        List<dynamic> responseData = jsonDecode(response.body);
        setState(() {
          genres =
              responseData.cast<String>().map((e) => Genre(name: e)).toList();
        });
      });
    } else {
      // If the server did not return a 200 OK response,
      // then throw an exception.
      print(response.statusCode);
    }
  }

  void onSave() async {
    final response = await http.post(
        Uri.parse("http://localhost:8080/rate/genres"),
        headers: <String, String>{
          'Content-Type': 'application/json; charset=UTF-8',
        },
        body: json.encode({
          "userId": "test123",
          "ratings": genres.map((e) => e.toJson()).toList()
        }));

    if (response.statusCode == 200) {
      Navigator.push(
          context, MaterialPageRoute(builder: (context) => const MyHomePage()));
    } else {
      print(response.statusCode);
    }
  }

  void itemChange(bool val, int index) {
    setState(() {
      genres[index].liked = val;
    });
  }

  @override
  void initState() {
    super.initState();
    fetchGenres();
  }

  @override
  Widget build(BuildContext context) {
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
