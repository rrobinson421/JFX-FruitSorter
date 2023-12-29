package fruitsorter.api;

import javafx.application.Application;
import javafx.stage.Stage;
import java.nio.charset.StandardCharsets;
import javafx.application.Platform;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Arrays;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import java.io.IOException;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import java.util.Random;
import java.io.FileInputStream;
import java.util.Properties;
import java.io.File;
import java.util.Scanner;
import javafx.geometry.Pos;
import javafx.geometry.Insets;
import java.io.FileNotFoundException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
//Scene
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.Priority;
import javafx.scene.control.TextArea;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.RadioButton;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.scene.control.TextField;
import javafx.scene.control.Slider;
import javafx.scene.Group;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.ComboBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
//Net
import java.net.http.HttpClient;
import java.net.URI;
import java.net.URL;
import java.net.URLEncoder;
import java.net.http.HttpResponse;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse.BodyHandlers;
//Google
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import io.github.cdimascio.dotenv.Dotenv;
import io.github.cdimascio.dotenv.DotenvException;

public class App extends Application {

    /** HTTP client. */
    public static final HttpClient HTTP_CLIENT = HttpClient.newBuilder()
        .version(HttpClient.Version.HTTP_2)
        .followRedirects(HttpClient.Redirect.NORMAL)
        .build();

    /** Google object for parsing JSON-formatting */
    public static Gson GSON = new GsonBuilder()
        .setPrettyPrinting()
        .create();

    // ERROR WITH GSON PARSING

    //Object Setting
    Stage stage;
    Scene mainScene;
    Scene optionScene;
    Scene resultScene;
    Label mainInfoLabel = new Label(" Select the start button to begin!");
    Label optionInfoLabel = new Label();
    Label resultInfoLabel = new Label ("Result(s): ");
    VBox mainMenu;
    VBox optionMenu;
    VBox resultMenu;
    ImageView mainIconView;
    VBox mainIconLayer;
    VBox mainStartLayer;
    VBox optionSelectLayer;
    HBox optionGenerateLayer;
    ProgressBar pBar;
    HBox resultInfoLayer;
    HBox resultHolder;
    VBox resultShopLayer;
    VBox resultImageLayer;
    HBox resultButtonLayer;
    TextArea fruitInfo;
    ImageView fruitImageView;
    String resultLabelString = "";
    HBox mainLabelLayer;
    HBox optionLabelLayer;
    HBox resultLabelLayer;
    HBox optionOverLabelLayer;
    HBox optionProgressLayer;
    Button startButton;
    Button creditsButton;
    Button generateButton;
    Button shopButton;
    Button nextButton;
    Button randomButton;
    Button previousButton;
    Button optionBackButton;
    Button resultBackButton;
    RadioButton anyButton;
    HBox carLayer;
    HBox pLayer;
    HBox fLayer;
    HBox calLayer;
    HBox sLayer;
    Slider carSlider;
    Slider pSlider;
    Slider fSlider;
    Slider calSlider;
    Slider sSlider;
    TextField locationBar;
    HBox optionBackLayer;
    HBox resultBackLayer;
    Text carText = new Text("   Carbs");
    Text pText = new Text(" Protein");
    Text fText = new Text("      Fat");
    Text calText = new Text("Calories");
    Text sText = new Text("   Sugar");
    TextField carField = new TextField("0");
    TextField pField = new TextField("0");
    TextField fField = new TextField("0");
    TextField calField = new TextField("0");
    TextField sField = new TextField("0");
    double maxFinalCar = 30; //27.1
    double maxFinalP = 9; //9
    double maxFinalF = 15; //14.66
    double maxFinalCal = 160; //160
    double maxFinalS = 18; //18
    int minCar = 0;
    int minP = 0;
    int minF = 0;
    int minCal = 0;
    int minS = 0;
    int maxCar = 0;
    int maxP = 0;
    int maxF = 0;
    int maxCal = 0;
    int maxS = 0;
    boolean anyValue = false;
    String fruitUrl = "";
    String body = "";
    int apiResults = 0;
    ArrayList<Integer> idList;
    String[] nutrientUrls;
    boolean crashAlert = false;
    String crashUrl = "";
    ArrayList<Integer> tempList;
    HashMap<Integer, Integer> count;
    boolean initialParse = true;
    String message = "";
    Random rand;
    final String configPath = "resources/config.properties";
    String storeBody = "";
    boolean quotaLimit = false;
    ArrayList<TextArea> textAreaArray;
    ArrayList<String> fruitNameArray;
    static int arrayIndex = 0;
    String apiKey = "";
    ArrayList<Image> imageArray;
    int pBarCounter = 0;
    String creditText = "";
    ComboBox<String> dropDownMenu;
    HBox resultValueLayer;
    String resultActiveValues = "";
    Text resultNumber = new Text("");
    Text mainTitle = new Text("Fruit Generator");

    public App() {
        mainMenu = new VBox();
        optionMenu = new VBox();
        resultMenu = new VBox(5);
        mainScene = new Scene(mainMenu);
        optionScene = new Scene(optionMenu);
        resultScene = new Scene(resultMenu);
        mainIconView = new ImageView();
        mainIconLayer = new VBox();
        mainStartLayer = new VBox(10);
        optionSelectLayer = new VBox(10);
        optionGenerateLayer = new HBox();
        resultInfoLayer = new HBox();
        resultHolder = new HBox();
        resultShopLayer = new VBox(10);
        resultImageLayer = new VBox();
        resultButtonLayer = new HBox(10);
        fruitInfo = new TextArea();
        fruitImageView = new ImageView();
        optionOverLabelLayer = new HBox();
        optionProgressLayer = new HBox();
        mainLabelLayer = new HBox();
        optionLabelLayer = new HBox();
        resultLabelLayer = new HBox();
        locationBar = new TextField("Athens");
        optionBackLayer = new HBox();
        resultBackLayer = new HBox(235);
        pBar = new ProgressBar();
        startButton = new Button("Start");
        creditsButton = new Button ("Credits");
        generateButton = new Button("Generate");
        shopButton = new Button("Stores");
        nextButton = new Button("Next");
        randomButton = new Button("Random");
        previousButton = new Button("Previous");
        optionBackButton = new Button("Back");
        resultBackButton = new Button("Back");
        resultValueLayer = new HBox();
        anyButton = new RadioButton("All");
        carLayer = new HBox(5);
        pLayer = new HBox(5);
        fLayer = new HBox(5);
        calLayer = new HBox(5);
        sLayer = new HBox(5);
        carSlider = new Slider();
        pSlider = new Slider();
        fSlider = new Slider();
        calSlider = new Slider();
        sSlider = new Slider();
        idList = new ArrayList<Integer>();
        tempList = new ArrayList<Integer>();
        count = new HashMap<Integer, Integer>();
        nutrientUrls = new String[5];
        rand = new Random();
        textAreaArray = new ArrayList<TextArea>();
        fruitNameArray = new ArrayList<String>();
        imageArray = new ArrayList<Image>();
        dropDownMenu = new ComboBox<String>();
    } //App

    @Override
    public void init() {
        //Initialize expected scene graph and structure
        //Adding scene objects
        addMainMenuObjects();
        addOptionMenuObjects();
        addResultMenuObjects();
        defaultStart();

        //Creating lambdas
        this.startButton.setOnAction(event -> this.optionSceneMethod());
        this.creditsButton.setOnAction(event -> this.credits());
        this.generateButton.setOnAction(event -> this.generateFruit());
        this.shopButton.setOnAction(event -> this.generateStoreUrls());
        this.nextButton.setOnAction(event -> this.nextFruit());
        this.randomButton.setOnAction(event -> this.randomFruit());
        this.previousButton.setOnAction(event -> this.previousFruit());
        this.optionBackButton.setOnAction(event -> this.setScene(mainScene));
        this.resultBackButton.setOnAction(event -> this.optionSceneMethod());
    } // init

    @Override
    public void start(Stage stage) {
        this.stage = stage;
        setScene(mainScene);
    } // start

    public void stop() {
        System.out.println("stop() called");
    } // stop

    /**
     * The optionSceneMethod is called whenever the optionScene should
     * be loaded in order to reset the progress bar.
     */
    public void optionSceneMethod() {
        generateButton.setDisable(false);
        pBar.setProgress(0.0F);
        setScene(optionScene);
    } // startScene

    /**
     * The setScene method calls a new scene based on the input parameter.
     * @param scene the specific scene being called to the stage.
     */
    public void setScene(Scene scene) {
        Runnable taskSetScene = () -> {
            stage.setTitle("Fruit Generator Application");
            stage.setScene(scene);
            stage.setOnCloseRequest(event -> Platform.exit());
            stage.sizeToScene();
            stage.show();
            Platform.runLater(() -> this.stage.setResizable(false));
        };
        Platform.runLater(taskSetScene);
    } // setScene

    /**
     * The addMainMenuObjects method sets up most of the objects
     * that make up the main menu in order to simplify the init method.
     */
    public void addMainMenuObjects() {
        mainMenu.getChildren().
            addAll(mainIconLayer, mainStartLayer, mainLabelLayer);
        mainIconLayer.getChildren().addAll(mainIconView, mainTitle);
        mainStartLayer.getChildren().addAll(startButton, creditsButton);
    } // addMainMenuObjects

    /**
     * The addOptionMenuObjects method sets up most of the objects
     * that make up the option menu in order to simplify the init method.
     */
    public void addOptionMenuObjects() {
        optionMenu.getChildren().
            addAll(optionOverLabelLayer, optionSelectLayer, optionGenerateLayer, optionLabelLayer);
        optionOverLabelLayer.getChildren().addAll(optionBackLayer, optionProgressLayer);
        optionBackLayer.getChildren().add(optionBackButton);
        optionProgressLayer.getChildren().add(pBar);
        optionSelectLayer.getChildren().
            addAll(anyButton, carLayer, pLayer, fLayer, calLayer, sLayer);
        carLayer.getChildren().addAll(carText, carField, carSlider);
        pLayer.getChildren().addAll(pText, pField, pSlider);
        fLayer.getChildren().addAll(fText, fField, fSlider);
        calLayer.getChildren().addAll(calText, calField, calSlider);
        sLayer.getChildren().addAll(sText, sField, sSlider);
        optionGenerateLayer.getChildren().add(generateButton);
    } // addOptionMenuObjects

    /**
     * The addResultMenuObjects method sets up most of the objects
     * that make up the result menu in order to simplify the init method.
     */
    public void addResultMenuObjects() {
        resultMenu.getChildren().
            addAll(resultBackLayer, resultInfoLayer,
                resultHolder, resultButtonLayer, resultLabelLayer);
        resultHolder.getChildren().addAll(resultShopLayer, resultImageLayer);
        resultValueLayer.getChildren().add(resultNumber);
        resultBackLayer.getChildren().addAll(resultBackButton, resultValueLayer);
        resultInfoLayer.getChildren().addAll(fruitInfo);
        resultShopLayer.getChildren().addAll(shopButton, new Label("Enter City and State:"),
            locationBar, dropDownMenu);
        resultImageLayer.getChildren().add(fruitImageView);
        resultButtonLayer.getChildren().addAll(previousButton, randomButton, nextButton);
    } // addResultMenuObjects

    /**
     * The defaultStart method set starting values for almost every visual
     * object and many variables.
     */
    public void defaultStart() {
        resultLabelString = "\n Click the stores button to view markets with the " +
            "\n current fruit. (In some cases the closest locations are used.)";
        Image mainIcon = new Image("file:resources/raspberrylogo.png");
        mainIconView.setImage(mainIcon);
        mainLabelLayer.getChildren().add(mainInfoLabel);
        optionLabelLayer.getChildren().add(optionInfoLabel);
        resultLabelLayer.getChildren().add(resultInfoLabel);
        optionInfoLabel.setText(" Enter the minimum values in the boxes on the left." +
            "\n Select the maximum value using the sliders. (All in grams.) " +
            "\n Some maximum values are rounded up," +
            " so lower the\n specified slider if no results return.");
        mainMenu.setMaxWidth(400);
        mainMenu.setPrefWidth(400);
        mainMenu.setMaxHeight(500);
        mainMenu.setPrefHeight(500);
        mainMenu.setAlignment(Pos.CENTER);
        mainIconLayer.setMaxHeight(300);
        mainIconLayer.setPrefHeight(300);
        mainIconLayer.setAlignment(Pos.CENTER);
        mainStartLayer.setMaxHeight(175);
        mainStartLayer.setPrefHeight(175);
        mainStartLayer.setAlignment(Pos.CENTER);
        mainInfoLabel.setMaxHeight(25);
        mainInfoLabel.setPrefHeight(25);
        mainInfoLabel.setAlignment(Pos.CENTER);
        startButton.setMaxSize(200, 50);
        creditsButton.setMaxSize(200, 50);
        mainIconView.setPreserveRatio(true);
        mainIconView.setFitWidth(250);
        mainIconView.setFitHeight(250);
        mainTitle.setFont(Font.
            font("Trebuchet MS", FontWeight.BOLD, FontPosture.REGULAR, 35));
        mainTitle.setFill(Color.rgb(62, 0, 0));
        mainTitle.setStrokeWidth(0.5);
        mainTitle.setStroke(Color.BLACK);
        mainIconView.setFitHeight(250);
        mainIconView.setFitWidth(250);
        sliderSetter();
        resultSetter();
        optionSetter();

        arrayIndex = 0;
        pBar.setProgress(0.0F);
        pBarCounter = 0;
    } // defaultStart

    /**
     * The sliderSetter method adjusts the visual settings for the
     * sliders found in the option menu in order to simplify the
     * defaultStart method.
     */
    public void sliderSetter() {
        fruitInfo.setEditable(false);
        carField.setPrefWidth(50);
        pField.setPrefWidth(50);
        fField.setPrefWidth(50);
        calField.setPrefWidth(50);
        sField.setPrefWidth(50);
        carSlider.setPrefWidth(250);
        pSlider.setPrefWidth(250);
        fSlider.setPrefWidth(250);
        calSlider.setPrefWidth(250);
        sSlider.setPrefWidth(250);
        carSlider.setMin(0);
        pSlider.setMin(0);
        fSlider.setMin(0);
        calSlider.setMin(0);
        sSlider.setMin(0);
        carSlider.setMax(maxFinalCar);
        pSlider.setMax(maxFinalP);
        fSlider.setMax(maxFinalF);
        calSlider.setMax(maxFinalCal);
        sSlider.setMax(maxFinalS);
        carSlider.setValue(maxFinalCar);
        pSlider.setValue(maxFinalP);
        fSlider.setValue(maxFinalF);
        calSlider.setValue(maxFinalCal);
        sSlider.setValue(maxFinalS);
        carSlider.setSnapToTicks(true);
        pSlider.setSnapToTicks(true);
        fSlider.setSnapToTicks(true);
        calSlider.setSnapToTicks(true);
        sSlider.setSnapToTicks(true);
        carSlider.setShowTickLabels(true);
        pSlider.setShowTickLabels(true);
        fSlider.setShowTickLabels(true);
        calSlider.setShowTickLabels(true);
        sSlider.setShowTickLabels(true);
        carSlider.setMajorTickUnit(1);
        pSlider.setMajorTickUnit(1);
        fSlider.setMajorTickUnit(1);
        calSlider.setMajorTickUnit(1);
        sSlider.setMajorTickUnit(1);
        carSlider.setMinorTickCount(0);
        pSlider.setMinorTickCount(0);
        fSlider.setMinorTickCount(0);
        calSlider.setMinorTickCount(0);
        sSlider.setMinorTickCount(0);
        carSlider.setBlockIncrement(1);
        pSlider.setBlockIncrement(1);
        fSlider.setBlockIncrement(1);
        calSlider.setBlockIncrement(1);
        sSlider.setBlockIncrement(1);
    } // sliderSetter

    /**
     * The resultSetter method adjusts the visual settings for the
     * objects found in the result menu in order to simplify the
     * defaultStart method.
     */
    public void resultSetter() {
        resultValueLayer.setAlignment(Pos.CENTER_RIGHT);
        resultButtonLayer.setPrefWidth(400);
        resultMenu.setMaxWidth(400);
        resultMenu.setPrefWidth(400);
        resultMenu.setMaxHeight(500);
        resultMenu.setPrefHeight(500);
        resultMenu.setAlignment(Pos.CENTER);
        resultBackLayer.setPrefHeight(25);
        resultBackLayer.setMaxHeight(25);
        resultBackButton.setAlignment(Pos.CENTER_LEFT);
        resultInfoLayer.setPrefHeight(175);
        resultInfoLayer.setMaxHeight(175);
        resultHolder.setPrefHeight(175);
        resultHolder.setMaxHeight(175);
        resultShopLayer.setPrefHeight(175);
        resultShopLayer.setPrefWidth(200);
        resultImageLayer.setPrefHeight(175);
        resultImageLayer.setPrefWidth(200);
        fruitImageView.setFitHeight(150);
        fruitImageView.setFitWidth(150);
        fruitImageView.setPreserveRatio(true);
        resultShopLayer.setAlignment(Pos.CENTER);
        resultImageLayer.setAlignment(Pos.CENTER);
        resultButtonLayer.setAlignment(Pos.CENTER);
        resultButtonLayer.setPrefHeight(50);
        resultButtonLayer.setMaxHeight(50);
        resultLabelLayer.setPrefHeight(75);
        resultLabelLayer.setMaxHeight(75);
        resultLabelLayer.setAlignment(Pos.CENTER_LEFT);
        shopButton.setMaxHeight(40);
        shopButton.setPrefHeight(40);
        shopButton.setMaxWidth(120);
        shopButton.setPrefWidth(120);
        shopButton.setAlignment(Pos.CENTER);
        previousButton.setMaxHeight(30);
        previousButton.setMaxWidth(100);
        previousButton.setPrefHeight(30);
        previousButton.setPrefWidth(100);
        nextButton.setMaxHeight(30);
        nextButton.setMaxWidth(100);
        nextButton.setPrefHeight(30);
        nextButton.setPrefWidth(100);
        randomButton.setMaxHeight(30);
        randomButton.setMaxWidth(100);
        randomButton.setPrefHeight(30);
        randomButton.setPrefWidth(100);
        locationBar.setPrefWidth(80);
        locationBar.setPrefHeight(25);
        locationBar.setAlignment(Pos.CENTER);
        dropDownMenu.setMaxWidth(70);
        ObservableList<String> dropDownList = FXCollections.observableArrayList();
        dropDownList.addAll("AL", "AK", "AZ", "AR", "CA", "CO", "CT", "DE", "FL",
            "GA", "HI", "ID", "IL", "IN", "IA", "KS", "KY", "LA", "ME", "MD", "MA",
            "MI", "MN", "MS", "MO", "MT", "NE", "NV", "NH", "NJ", "NM", "NY", "NC",
            "ND", "OH", "OK", "OR", "PA", "RI", "SC", "SD", "TN", "TX", "UT", "VT",
            "VA", "WA", "WV", "WI", "WY");
        dropDownMenu.setItems(dropDownList);
        dropDownMenu.getSelectionModel().select(9);
    } // resultSetter

    /**
     * The optionSetter method adjusts the visual settings for the
     * objects found in the option menu in order to simplify the
     * defaultStart method.
     */
    public void optionSetter() {
        optionMenu.setMaxWidth(400);
        optionMenu.setPrefWidth(400);
        optionMenu.setMaxHeight(500);
        optionMenu.setPrefHeight(500);
        optionMenu.setAlignment(Pos.CENTER);
        optionOverLabelLayer.setPrefHeight(25);
        optionBackLayer.setPrefHeight(25);
        optionBackLayer.setAlignment(Pos.CENTER_LEFT);
        optionProgressLayer.setPrefHeight(25);
        optionSelectLayer.setPrefHeight(275);
        optionSelectLayer.setAlignment(Pos.CENTER_LEFT);
        anyButton.setAlignment(Pos.CENTER);
        optionGenerateLayer.setPrefHeight(130);
        optionGenerateLayer.setMaxHeight(130);
        optionGenerateLayer.setPrefWidth(400);
        optionGenerateLayer.setMaxWidth(400);
        optionGenerateLayer.setAlignment(Pos.CENTER);
        generateButton.setMaxHeight(75);
        generateButton.setPrefHeight(75);
        generateButton.setMaxWidth(250);
        generateButton.setPrefWidth(250);
        optionLabelLayer.setPrefHeight(70);
        optionOverLabelLayer.setPrefWidth(400);
        optionProgressLayer.setPrefWidth(320);
        optionBackLayer.setPrefWidth(80);
        optionProgressLayer.setAlignment(Pos.CENTER);
        pBar.prefWidthProperty().bind(optionProgressLayer.widthProperty());
    } //optionSetter

    /**
     * The credits method reads the contents of the ATTRIBUTION.md file
     * using a Scanner in order to allow the user to read the attributions
     * for the images used.
     */
    public void credits() {
        Runnable taskCredits = () -> {
            try {
                creditText = "";
                File creditFile = new File("meta/ATTRIBUTION.md");
                Scanner scnr = new Scanner(creditFile);
                while (scnr.hasNextLine()) {
                    creditText += scnr.nextLine() + "\n";
                } // while
                scnr.close();
            } catch (FileNotFoundException fnfe) {
                System.err.print(fnfe);
            } // try
            TextArea text = new TextArea(creditText);
            text.setEditable(false);
            text.setWrapText(true);
            Alert creditInfo = new Alert(AlertType.INFORMATION);
            creditInfo.setTitle("Credits Panel");
            creditInfo.setHeaderText(" ATTRIBUTION Credits:");
            creditInfo.getDialogPane().setContent(text);
            creditInfo.setResizable(false);
            creditInfo.showAndWait();
        };
        Platform.runLater(taskCredits);
    } // credits

    /**
     * The setMinMaxValues method retrieves the user input for the
     * chosen minimum and maximum values displayed on the optionScene.
     */
    public void setMinMaxValues() {
        minCar = Integer.parseInt(carField.getText().trim());
        minP = Integer.parseInt(pField.getText().trim());
        minF = Integer.parseInt(fField.getText().trim());
        minCal = Integer.parseInt(calField.getText().trim());
        minS = Integer.parseInt(sField.getText().trim());
        maxCar = (int) carSlider.getValue();
        maxP = (int) pSlider.getValue();
        maxF = (int) fSlider.getValue();
        maxCal = (int) calSlider.getValue();
        maxS = (int) sSlider.getValue();
        if (minCar > maxCar || minP > maxP || minF > maxF || minCal > maxCal || minS > maxS) {
            throw new IndexOutOfBoundsException();
        } // if
    } // setMinMaxValues

     /**
     * The generateFruit method acts as a command center, calling specific
     * methods in order, while ensuring some methods aren't called when an
     * error is displayed.
     */
    public void generateFruit() {
        Runnable taskGenerateFruit = () -> {
            optionBackButton.setDisable(true);
            pBarCounter = 0;
            generateButton.setDisable(true);
            try {
                setMinMaxValues();
                if (anyButton.isSelected()) {
                    anyValue = true;
                } else {
                    anyValue = false;
                } // if
                generateRequest();
                if (!crashAlert) {
                    textAreaArray.clear();
                    fruitNameArray.clear();
                    imageArray.clear();
                    fruitIdRequest();
                    pBar.setProgress(100);
                    optionBackButton.setDisable(false);
                    infoSetter(0);
                    previousButton.setDisable(true);
                    setScene(resultScene);
                } // if
            } catch (NumberFormatException e) {
                crashAlertMethod(5);
            } catch (IndexOutOfBoundsException e) {
                crashAlertMethod(6);
            } // try
        };
        Thread taskThread = new Thread(taskGenerateFruit);
        taskThread.setDaemon(true);
        taskThread.start();
    } // generateFruit

    /**
     * The generateRequest method resets urls and alerts, while either
     * calling the allFruit method or multiFruit method depending on the
     * user's input during the optionScene.
     */
    public void generateRequest() {
        crashAlert = false;
        initialParse = true;
        idList.clear();
        fruitUrl = "";
        crashUrl = "";
        if (anyValue || (minCar == 0 && maxCar == maxFinalCar &&
            minP == 0 && maxP == maxFinalP && minF == 0 && maxF == maxFinalF &&
            minCal == 0 && maxCal == maxFinalCal && minS == 0 && maxS == maxFinalS)) {
            allFruit();
        } else {
            setNutrientUrlArray();
            multiFruit();
        } // else
    } // generateRequest

    /**
     * The all fruit method sets the fruitUrl to one that will return
     * all possible fruit.
     */
    public void allFruit() {
        fruitUrl += "https://fruityvice.com/api/fruit/all";
        fruitRequest(fruitUrl);
    } // allFruit

    /**
     * The setNutrientUrlArray method generates five different fruitUrls
     * and sets them inside the nutrientUrls array to be called later.
     */
    public void setNutrientUrlArray() {
        nutrientUrls[0] = "carbohydrates?min=" + minCar + "&max=" + maxCar;
        nutrientUrls[1] = "protein?min=" + minP + "&max=" + maxP;
        nutrientUrls[2] = "fat?min=" + minF + "&max=" + maxF;
        nutrientUrls[3] = "calories?min=" + minCal + "&max=" + maxCal;
        nutrientUrls[4] = "sugar?min=" + minS + "&max=" + maxS;
    } //setNutrientUrlArray

    /**
     * The multiFruit array makes five different requests to the fruitRequest
     * array using the urls stored in the nutrientUrls array.
     * If there is no crash, the sortIds array is called.
     */
    public void multiFruit() {
        for (int i = 0; i < 5; i++) {
            fruitUrl = "https://fruityvice.com/api/fruit/";
            fruitUrl += nutrientUrls[i];
            crashUrl = fruitUrl;
            fruitRequest(fruitUrl);
        } // for
        if (!crashAlert) {
            sortIds();
            crashUrl = "";
        } // if
    } // multiFruit

    /**
     * The fruitRequest method intakes a url String, converts it into
     * a URI and sends a request to the FruitviceAPI to return a response
     * stored in the body.
     * This method is called in two different stages of the program and
     * differentiates the two by the intialParse boolean.
     * @param url the constructed String url input from other methods.
     */
    public void fruitRequest(String url) {
        URI fruitUri = URI.create(url);
        HttpRequest request = HttpRequest.newBuilder()
            .uri(fruitUri).build();
        try {
            HttpResponse<String> response =
                HTTP_CLIENT.send(request, BodyHandlers.ofString());
            body = response.body();
            if (response.statusCode() != 200) {
                throw new IOException();
            } // if
            if (initialParse) {
                gsonIdParse();
            } else {
                gsonFinalParse();
            } // if
        } catch (IOException | InterruptedException e) {
            if (crashUrl.equals("")) {
                crashAlertMethod(1);
            } else {
                crashAlertMethod(0);
            } // if
        } // try
    } // fruitRequest

    /**
     * The gsonIdParse method takes the body from the first
     * instance of the fruitRequest method and stores each required fruit's
     * special id within an idList.
     */
    public void gsonIdParse() {
        FruitResponse[] apiResponse = GSON.
            fromJson(body, FruitResponse[].class);
        apiResults = apiResponse.length;
        for (FruitResponse name : apiResponse) {
            int id = name.id;
            idList.add(id);
        } // for
    } // gsonIdParse

    /**
     * The gsonFinalParse method uses the id's from the idList to
     * return specific nutrient information from all of the newly ordered
     * fruits, storing the information within a TextArea array.
     */
    public void gsonFinalParse() {
        FruitResponse apiFinalResponse = GSON.
            fromJson(body, FruitResponse.class);
        String finName = apiFinalResponse.name;
        double carbs = apiFinalResponse.nutritions.carbohydrates;
        double protein = apiFinalResponse.nutritions.protein;
        double fat = apiFinalResponse.nutritions.fat;
        double calories = apiFinalResponse.nutritions.calories;
        double sugar = apiFinalResponse.nutritions.sugar;
        String value = "";
        value += "Fruit: " + finName + "\n\nNutrition Information (per 100g):\n    Carbohydrates: "
            + carbs + "g\n    Protein: " + protein + "g\n    Fat: " + fat + "g\n    Calories: "
            + calories + "g\n    Sugar: " + sugar + "g";
        TextArea tempArea = new TextArea(value);
        textAreaArray.add(tempArea);
        fruitNameArray.add(finName);
        setImages(finName.toLowerCase());
    } // gsonFinalParse

    /**
     * The sortIds method takes all of the ids returned from all
     * five calls by the multiFruit array, and returns only those
     * that appear exactly five times.
     */
    public void sortIds() {
        for (int i = 0; i < idList.size(); i++) {
            int value = idList.get(i);
            if (count.containsKey(value)) {
                count.put(value, count.get(value) + 1);
            } else {
                count.put(value, 1);
            } //if
        } // for
        for (Integer n : count.keySet()) {
            if (count.get(n) == 5) {
                tempList.add(n);
            } // if
        } // for
        idList.clear();
        idList.addAll(tempList);
        tempList.clear();
        count.clear();
        if (idList.size() < 2) {
            randomButton.setDisable(true);
            nextButton.setDisable(true);
        } else {
            randomButton.setDisable(false);
            nextButton.setDisable(false);
        } // if
    } // sortIds

     /**
     * The fruitIdRequest method makes the calls to the fruitRequest
     * method a second time using the ids from the idList.
     */
    public void fruitIdRequest() {
        resultInfoLabel.setText(" Result(s): " + idList.size() + resultLabelString);
        initialParse = false;
        for (int i = 0; i < idList.size(); i++) {
            fruitUrl = "https://fruityvice.com/api/fruit/";
            fruitUrl += idList.get(i);
            fruitRequest(fruitUrl);
        } // for
        if (idList.size() == 0) {
            crashAlertMethod(1);
        } // if
    } // fruitIdRequest

    /**
     * The crashAlertMethod is used to create error pop-ups
     * for mulitple different parts of the application based on
     * the err code input as a parameter.
     * @param err the error value that specifies the error message returned.
     */
    public void crashAlertMethod(int err) {
        Runnable taskCrashAlertMethod = () -> {
            try {
                generateButton.setDisable(false);
                crashAlert = true;
                optionBackButton.setDisable(false);
                throw new IllegalArgumentException();
            } catch (IllegalArgumentException e) {
                if (err == 0) {
                    message = "Invalid Url: " + crashUrl + "\n\nException: " + e
                        + "\n\nPlease re-generate with valid values.";
                } else if (err == 1) {
                    message = "0 results returned. \n\nException: " + e
                        + "\n\nPlease re-generate with valid values.";
                } else if (err == 2) {
                    message = "The daily quota has been reached for searching. \n\nException: " + e
                        + "\n\nPlease try again tomorrow.";
                    quotaLimit = false;
                } else if (err == 3) {
                    message = "The request for stores couldn't go through. \n\nException: " + e
                        + "\n\nPlease try again.";
                } else if (err == 4) {
                    message = "The request for stores in this area couldn't be found. " +
                        "\n\nException " + e + "\n\nPlease try again, with another fruit.";
                } else if (err == 5) {
                    message = "Please choose a valid numeric value.";
                } else if (err == 6) {
                    message = "Please choose a numeric value with bounds." +
                        "\n\nThe value within the boxes are the minimum values.";
                } // if
                TextArea text = new TextArea(message);
                text.setEditable(false);
                Alert alert = new Alert(AlertType.ERROR);
                alert.setTitle("Error Panel");
                alert.setHeaderText(" An error has been detected.");
                alert.getDialogPane().setContent(text);
                alert.setResizable(false);
                alert.showAndWait();
            } // try
        };
        Platform.runLater(taskCrashAlertMethod);
    } // crashAlertMethod

     /**
     * The setImages method uses the name of a fruit
     * to create an Image object that uses the pre-downloaded images
     * and adds them to an array.
     * @param name the name of the fruit used to return a specific image.
     */
    public void setImages(String name) {
        String imageFile = "file:resources/" + name.toLowerCase() + "image.jpg";
        Image image = new Image(imageFile);
        imageArray.add(image);
        setProgress(1.0 * pBarCounter / idList.size());
        pBarCounter++;
    } // setImages

    /**
     * The setProgress method changes the amount the progress bar displays
     * based on the parameter input.
     * @param input the value for the progress bar to be set to.
     */
    public void setProgress(double input) {
        Platform.runLater(() -> pBar.setProgress(input));
    } // setProgress

    /**
     * The nextFruit method is called when clicking the next
     * button on the results scene. This cycles to the next fruit
     * information and image.
     */
    public void nextFruit() {
        if (textAreaArray.get(arrayIndex + 1) != null) {
            infoSetter(arrayIndex + 1);
        } // if
    } // nextFruit

    /**
     * The previousFruit method is called when clicking the previous
     * button on the results scene. This cycles to the previous fruit
     * information and image.
     */
    public void previousFruit() {
        if (textAreaArray.get(arrayIndex - 1) != null) {
            infoSetter(arrayIndex - 1);
        } // if
    } // previousFruit

    /**
     * The randomFruit method is called when clicking the random
     * button on the results scene. This randomly chooses a not shown
     * fruit and shows its information and image.
     */
    public void randomFruit() {
        int randomIndex = rand.nextInt(idList.size());
        if (randomIndex == arrayIndex) {
            randomFruit();
        } else {
            infoSetter(randomIndex);
        } // if
    } // randomFruit

    /**
     * The infoSetter method sets the active text area and
     * image for the fruit specified at an index in its respective array.
     * @param index the index for a fruit's information and image.
     */
    public void infoSetter(int index) {
        Runnable taskInfoSetter = () -> {
            fruitInfo.setText(textAreaArray.get(index).getText());
            fruitImageView.setImage(imageArray.get(index));
            arrayIndex = index;
            resultActiveValues = ((index + 1) + " / " + textAreaArray.size());
            resultNumber.setText("Result:  " + resultActiveValues);
            if (arrayIndex > 0) {
                previousButton.setDisable(false);
            } else {
                previousButton.setDisable(true);
            } // if
            if (arrayIndex < idList.size() - 1) {
                nextButton.setDisable(false);
            } else {
                nextButton.setDisable(true);
            } // if
        };
        Platform.runLater(taskInfoSetter);
    } // infoSetter

    /**
     * The generateApiKey method assigns a unique APIKey to the
     * apiKey String so the YelpAPI can be used.
     */
    public void generateApiKey() {
        try {
            Dotenv dotenv = Dotenv.load();
            apiKey = dotenv.get("YELP_API_KEY");
        } catch (NullPointerException npe) {
            System.err.println(npe);
        } // try
    } // generateApiKey

    /**
     * The generateStoreUrls method creates a url that will be
     * used to request store information from the YelpAPI based
     * on user input.
     */
    public void generateStoreUrls() {
        generateApiKey();
        String endpoint = "https://api.yelp.com/v3/businesses/search";
        String term = fruitNameArray.get(arrayIndex);
        String state = dropDownMenu.getValue();
        String tempCity = URLEncoder.encode(locationBar.getText(), StandardCharsets.UTF_8);
        String city = tempCity.replace("+", "%20");
        String tempUrl = "";
        tempUrl += endpoint + "?term=" + term + "&location=";
        if (city.length() != 0) {
            tempUrl += city + "%2C%20";
        } // if
        tempUrl += state + "&categories=stores&categories=markets&sort_by=best_match&limit=5";
        requestStores(tempUrl);
    } // requestStoreUrl

    /**
     * The requestStores method intakes a url and requests
     * store information from the YelpAPI using an APIKey and stores
     * the information in the storeBody.
     * @param url the String url used to process a request.
     */
    public void requestStores(String url) {
        try {
            URI storeUri = URI.create(url);
            HttpRequest storeRequest = HttpRequest.newBuilder()
                .uri(storeUri)
                .header("accept", "application/json")
                .header("Authorization", "Bearer " + apiKey)
                .method("GET", HttpRequest.BodyPublishers.noBody())
                .build();
            HttpResponse<String> storeResponse = HttpClient.newHttpClient()
                .send(storeRequest, HttpResponse.BodyHandlers.ofString());
            storeBody = storeResponse.body();
            parseStores();
            if (storeResponse.statusCode() == 429) {
                quotaLimit = true;
                throw new IOException();
            } else if (storeResponse.statusCode() != 200) {
                throw new IOException();
            } // if
        } catch (IOException | InterruptedException e) {
            if (quotaLimit) {
                crashAlertMethod(2);
            } else {
                crashAlertMethod(3);
            } // if
        } // try
    } // requestStores

    /**
     * The parseStores method takes specific information from the
     * storeBody and adds it to a String.
     */
    public void parseStores() {
        String storeValue = "";
        StoreResponse apiStoreResponse = GSON.
            fromJson(storeBody, StoreResponse.class);
        int storeTotal = apiStoreResponse.total;
        for (StoreBusinesses stores : apiStoreResponse.businesses) {
            String storeName = stores.name;
            double storeRating = stores.rating;
            String storePhone = stores.phone;
            String storeAddress1 = stores.location.address1;
            String storeAddress2 = stores.location.address2;
            String storeAddress3 = stores.location.address3;
            String storeCity = stores.location.city;
            String storeState = stores.location.state;
            String storeFullAddress = storeAddress1;
            if (storeAddress2 != null) {
                storeFullAddress += storeAddress2;
                if (storeAddress3 != null) {
                    storeFullAddress += storeAddress3;
                } // if
            } // if
            storeFullAddress += " " + storeCity + ", " + storeState;
            String storeTempValue = "Name: " + storeName + "\nRating: "
                + storeRating + "\nAddress: " + storeFullAddress + "\nPhone: "
                + storePhone;
            storeValue += storeTempValue + "\n\n";
        } // for
        if (storeTotal == 0) {
            crashAlertMethod(4);
        } else {
            showStores(storeValue);
        } // if
    } // parseStores

    /**
     * The showStores method is called when clicking the stores button
     * that creates a pop-up containing information about a few stores
     * that has a specific fruit in stock.
     * @param info the String that contains store information.
     */
    public void showStores(String info) {
        Runnable taskShowStores = () -> {
            TextArea text = new TextArea(info);
            text.setEditable(false);
            Alert storeInfo = new Alert(AlertType.INFORMATION);
            storeInfo.setTitle("Yelp Panel");
            storeInfo.setHeaderText(" List of curated stores: ");
            storeInfo.getDialogPane().setContent(text);
            storeInfo.setResizable(false);
            storeInfo.showAndWait();
        };
        Platform.runLater(taskShowStores);
    } // showStores

    //** AppDriver Class */
    public static void main(String[] args) {
        launch();
    }

}