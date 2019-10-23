set PATH_TO_FX="c:\Java-Mods\javafx-sdk-11.0.2\lib"
javac --module-path %PATH_TO_FX% --add-modules javafx.controls Main.java
java --module-path %PATH_TO_FX% --add-modules javafx.controls Main