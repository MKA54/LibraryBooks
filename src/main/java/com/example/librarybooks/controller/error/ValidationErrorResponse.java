package com.example.librarybooks.controller.error;

import java.util.List;

public record ValidationErrorResponse(List<Violation> violations) {
}
