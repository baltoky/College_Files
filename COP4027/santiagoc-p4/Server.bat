set PATH_TO_FX="E:\Documents\Java\javafx-sdk-11.0.2\lib"
javac --module-path %PATH_TO_FX% --add-modules javafx.controls tictactoe/Server.java
java --module-path %PATH_TO_FX% --add-modules javafx.controls tictactoe.Server
pause
rm *.class