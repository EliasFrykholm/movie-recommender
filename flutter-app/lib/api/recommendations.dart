import 'dart:convert';

import 'package:app/exceptions/unknown_user_exception.dart';
import 'package:app/models/movie_data.dart';
import 'package:app/utils/device_id.dart';
import 'package:http/http.dart' as http;

Future<List<MovieData>> fetchMovies() async {
  String? deviceId = await getDeviceId();
  if (deviceId == null) {
    throw Exception("Could not read device id");
  }
  final response = await http
      .get(Uri.parse('http://localhost:8080/recommendation/movie/' + deviceId));

  if (response.statusCode == 200) {
    List<dynamic> responseData = jsonDecode(response.body);
    return responseData.map((e) => MovieData.fromJson(e)).toList();
  } else if (response.statusCode == 401) {
    throw UnknownUserException();
  } else {
    print(response.statusCode);
    throw Exception('Failed to load data');
  }
}
