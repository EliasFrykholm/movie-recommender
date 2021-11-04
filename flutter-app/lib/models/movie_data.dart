class MovieData {
  final int tmdbId;
  final String title;
  final String description;
  final DateTime releaseDate;
  final List<String> genres;
  final int runtime;
  final double rating;
  final String coverArtUrl;
  final String trailerUrl;

  MovieData({
    required this.tmdbId,
    required this.description,
    required this.releaseDate,
    required this.genres,
    required this.runtime,
    required this.rating,
    required this.coverArtUrl,
    required this.trailerUrl,
    required this.title,
  });

  factory MovieData.fromJson(Map<String, dynamic> json) {
    return MovieData(
      tmdbId: json["tmdbId"],
      description: json["description"],
      releaseDate: DateTime.parse(json["releaseDate"]),
      genres: (json["genres"] as List<dynamic>).cast<String>(),
      runtime: json["runtime"],
      rating: json["rating"],
      coverArtUrl: json["coverArtUrl"],
      trailerUrl: json["trailerUrl"],
      title: json["title"],
    );
  }
}
