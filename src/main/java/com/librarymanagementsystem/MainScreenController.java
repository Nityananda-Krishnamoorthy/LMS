package com.librarymanagementsystem;

import java.sql.SQLException;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class MainScreenController {

    @FXML
    private TableView<Books> booksTableView;

    @FXML
    private TableColumn<Books, Integer> snoColumn;

    @FXML
    private TextField snoField;

    @FXML
    private TableColumn<Books, String> titleColumn;

    @FXML
    private TextField titleField;

    @FXML
    private TableColumn<Books, String> authorColumn;

    @FXML
    private TextField authorField;

    @FXML
    private TableColumn<Books, String> authorGenderColumn;

    @FXML
    private TextField authorGenderField;

    @FXML
    private TableColumn<Books, Integer> publicationYearColumn;

    @FXML
    private TextField publicationYearField;

    @FXML
    private TableColumn<Books, String> genreColumn;

    @FXML
    private TextField genreField;

    @FXML
    private TableColumn<Books, Double> bookPriceColumn;

    @FXML
    private TextField bookPriceField;

    @FXML
    private TableColumn<Books, Boolean> bookAvailabilityColumn;

    @FXML
    private TextField bookAvailabilityField;

    @FXML
    private TableColumn<Books, String> bookCoverColumn;

    @FXML
    private TextField bookCoverField;

    @FXML
    private TableColumn<Books, String> conditionColumn;

    @FXML
    private TextField conditionField;

    @FXML
    private TextField searchTextField;

    @FXML
    private Button btnDelete;

    @FXML
    private Button btnInsert;

    @FXML
    private Button btnRead;

    @FXML
    private Button btnUpdate;

    // @FXML
    // private Button locateBooksButton;

    // @FXML
    // private TextArea booksTextArea;

    // @FXML
    // private Label errorLabel;

    private final DatabaseHandler dbHandler = new DatabaseHandler();
    private FilteredList<Books> filteredData;

    @FXML
    public void initialize() {

        booksTableView.setOnMouseClicked(event -> {
    if (event.getClickCount() == 1) {
        Books selected = booksTableView.getSelectionModel().getSelectedItem();
        if (selected != null) {
            fillForm(selected);
            snoField.setDisable(true);
        }
    }
});



        // Configure table columns EXACTLY matching Books.java getters
        snoColumn.setCellValueFactory(new PropertyValueFactory<>("isbn"));
        titleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
        authorColumn.setCellValueFactory(new PropertyValueFactory<>("author"));
        authorGenderColumn.setCellValueFactory(new PropertyValueFactory<>("authorGender"));
        publicationYearColumn.setCellValueFactory(new PropertyValueFactory<>("publicationYear"));
        genreColumn.setCellValueFactory(new PropertyValueFactory<>("genre"));
        bookPriceColumn.setCellValueFactory(new PropertyValueFactory<>("bookPrice"));
        bookAvailabilityColumn.setCellValueFactory(new PropertyValueFactory<>("bookAvailability"));
        bookCoverColumn.setCellValueFactory(new PropertyValueFactory<>("bookCover"));
        conditionColumn.setCellValueFactory(new PropertyValueFactory<>("condition"));

        // Load data once UI is ready
        loadBooks();

    }

    private void loadBooks() {
        try {

            
            // new filtering logic can be added here using searchTextField's text property
            ObservableList<Books> booksList = dbHandler.getBooksAsObservableList();

            filteredData = new FilteredList<>(booksList, b -> true);

            // Bind search field
            searchTextField.textProperty().addListener((obs, oldVal, newVal) -> {
                filteredData.setPredicate(book -> {
                    if (newVal == null || newVal.isBlank()) return true;

                    String filter = newVal.toLowerCase();

                    return book.getTitle().toLowerCase().contains(filter)
                            || book.getAuthor().toLowerCase().contains(filter)
                            || book.getGenre().toLowerCase().contains(filter)
                            || book.getCondition().toLowerCase().contains(filter)
                            || String.valueOf(book.getPublicationYear()).contains(filter)
                            || String.valueOf(book.isBookAvailability()).toLowerCase().contains(filter);
                });
            });

            booksTableView.setItems(filteredData);

        } catch (SQLException e) {
            // errorLabel.setText("Error loading books: " + e.getMessage());
        }
    }

     @FXML
    void deleteBooks(ActionEvent event) {
    Books selectedBook = booksTableView.getSelectionModel().getSelectedItem();
    if (selectedBook == null) {
        showAlert(Alert.AlertType.WARNING, "No Selection", "Please select a book to delete.");
        return;
    }
    boolean success = dbHandler.deleteBooks(selectedBook);
    if (success) {
        showAlert(Alert.AlertType.INFORMATION, "Success", "Book deleted successfully.");
        clearFields();
        loadBooks();
    } else {
        showAlert(Alert.AlertType.ERROR, "Error", "Failed to delete the book.");
    }

    }

  @FXML
private void insertBooks() {
    try {
        snoField.setDisable(false);   // ensure editable for new entry
        String isbn = snoField.getText().trim();
        if (isbn.isEmpty()) {
            showAlert(Alert.AlertType.WARNING, "ISBN Required", "ISBN cannot be empty.");
            return;
        }
        // NEW: check duplicate
        if (dbHandler.isbnExists(isbn)) {
            showAlert(Alert.AlertType.ERROR,
                    "Duplicate ISBN",
                    "A book with this ISBN already exists. Please use a unique ISBN.");
            return;
        }

        Books newBook = new Books(
                isbn,
                titleField.getText(),
                authorField.getText(),
                authorGenderField.getText(),
                Integer.parseInt(publicationYearField.getText()),
                genreField.getText(),
                Double.parseDouble(bookPriceField.getText()),
                Boolean.parseBoolean(bookAvailabilityField.getText()),
                bookCoverField.getText(),
                conditionField.getText()
        );

        boolean success = dbHandler.insertBook(newBook);

        if (success) {
            showAlert(Alert.AlertType.INFORMATION, "Success", "Book inserted successfully.");
            clearFields();
            loadBooks();
        }
    } catch (Exception e) {
        showAlert(Alert.AlertType.ERROR, "Invalid Input", "Please verify the field values.");
    }
}


    @FXML
void readBooks(ActionEvent event) {
    Books selectedBook = booksTableView.getSelectionModel().getSelectedItem();

    if (selectedBook == null) {
        showAlert(Alert.AlertType.WARNING, "No Selection", "Please select a book from the table.");
        return;
    }

    fillForm(selectedBook);
    snoField.setDisable(true);
}



    @FXML
    void updateBooks(ActionEvent event) {
        snoField.setDisable(true); // ISBN should not be editable during update
    try {
        Books selectedBook = booksTableView.getSelectionModel().getSelectedItem();
        if (selectedBook == null) {
            showAlert(Alert.AlertType.WARNING, "No Selection", "Please select a book to update.");
            return;
        }

        Books updatedBook = new Books(
                selectedBook.getIsbn(),  // ISBN should remain unchanged
                titleField.getText(),
                authorField.getText(),
                authorGenderField.getText(),
                Integer.parseInt(publicationYearField.getText()),
                genreField.getText(),
                Double.parseDouble(bookPriceField.getText()),
                Boolean.parseBoolean(bookAvailabilityField.getText()),
                bookCoverField.getText(),
                conditionField.getText()
        );

        boolean success = dbHandler.updateBooks(updatedBook);

        if (success) {
            showAlert(Alert.AlertType.INFORMATION, "Success", "Book updated successfully.");
            clearFields();
            loadBooks();
        }
    } catch (Exception e) {
        showAlert(Alert.AlertType.ERROR, "Invalid Input", "Please verify the field values.");

    }
}

    private void fillForm(Books selected) {
    snoField.setText(selected.getIsbn());
    titleField.setText(selected.getTitle());
    authorField.setText(selected.getAuthor());
    authorGenderField.setText(selected.getAuthorGender());
    publicationYearField.setText(String.valueOf(selected.getPublicationYear()));
    genreField.setText(selected.getGenre());
    bookPriceField.setText(String.valueOf(selected.getBookPrice()));
    bookAvailabilityField.setText(String.valueOf(selected.isBookAvailability()));
    bookCoverField.setText(selected.getBookCover());
    conditionField.setText(selected.getCondition());
}


    private void clearFields() {
    snoField.clear();
    titleField.clear();
    authorField.clear();
    authorGenderField.clear();
    publicationYearField.clear();
    genreField.clear();
    bookPriceField.clear();
    bookAvailabilityField.clear();
    bookCoverField.clear();
    conditionField.clear();
    snoField.setDisable(false);
}

private void showAlert(Alert.AlertType alertType, String title, String message) {
    Alert alert = new Alert(alertType);
    alert.setTitle(title);
    alert.setHeaderText(null);
    alert.setContentText(message);
    alert.showAndWait();
}

}
