import 'dart:convert';

import 'package:app/utils/device_id.dart';
import 'package:http/http.dart' as http;

import '../genre_rating.dart';

void rateMovie(bool liked, int tmdbId) async {
  String? deviceId = await getDeviceId();
  if (deviceId == null) {
    throw Exception("Could not read device id");
  }
  final response = await http.post(
      Uri.parse("http://localhost:8080/rate/movie"),
      headers: <String, String>{
        'Content-Type': 'application/json; charset=UTF-8',
      },
      body:
          json.encode({"userId": deviceId, "tmdbId": tmdbId, "liked": liked}));

  if (response.statusCode != 200) {
    print(response.statusCode);
  }
}

Future<bool> rateGenres(List<Genre> genres) async {
  String? deviceId = await getDeviceId();
  if (deviceId == null) {
    throw Exception("Could not read device id");
  }
  final response = await http.post(
      Uri.parse("http://localhost:8080/rate/genres"),
      headers: <String, String>{
        'Content-Type': 'application/json; charset=UTF-8',
      },
      body: json.encode({
        "userId": deviceId,
        "ratings": genres.map((e) => e.toJson()).toList()
      }));

  if (response.statusCode == 200) {
    return true;
  } else {
    print(response.statusCode);
    throw Exception("Failed to rate genres");
  }
}
