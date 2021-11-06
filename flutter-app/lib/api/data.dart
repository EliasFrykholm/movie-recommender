import 'dart:convert';

import 'package:http/http.dart' as http;

import '../genre_rating.dart';

Future<List<Genre>> fetchGenres() async {
  final response =
      await http.get(Uri.parse("http://localhost:8081/moviedata/genres"));
  if (response.statusCode == 200) {
    List<dynamic> responseData = jsonDecode(response.body);
    return responseData.cast<String>().map((e) => Genre(name: e)).toList();
  } else {
    print(response.statusCode);
    throw Exception('Failed to load genres');
  }
}
