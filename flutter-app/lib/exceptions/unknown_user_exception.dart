class UnknownUserException implements Exception {
  final String _message;
  UnknownUserException([this._message = "Unknown user"]);

  @override
  String toString() {
    return "Exception: $_message";
  }
}
