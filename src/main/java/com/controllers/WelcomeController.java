package com.controllers;

import java.io.IOException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.PieChart;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

import com.language.App;
import com.model.Facade;

public class WelcomeController {

    @FXML
    void StartReview(ActionEvent event) throws IOException {
        App.setRoot("progress");
    }

    @FXML
    void StartStory(ActionEvent event) throws IOException {
        App.setRoot("Story");
    }

    @FXML
    void StartExercise(ActionEvent event) throws IOException {
        App.setRoot("MultipleChoiceQuestions");
    }

    @FXML
    void StartExercise1(ActionEvent event) throws IOException {
        App.setRoot("MultipleChoiceQuestions");
    }

    @FXML
    private void switchToPrimary() throws IOException {
        App.setRoot("primary");
    }

    @FXML
    private PieChart pieChart;
    
    @FXML
    private void initialize() {
        ObservableList<PieChart.Data> pieChartData = 
                FXCollections.observableArrayList(
                    new PieChart.Data("Progress", Facade.getInstance().getPercentageThroughCourse()),
                    new PieChart.Data("Excess", 100-Facade.getInstance().getPercentageThroughCourse()));

        pieChart.getData().addAll(pieChartData);

        progressText.setText("You've completed " + Facade.getInstance().getPercentageThroughCourse() + "% of this level. Keep learning!");
    }

    @FXML
    private Text progressText;

    
    @FXML
    private ImageView avatar;

}
