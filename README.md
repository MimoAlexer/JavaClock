# JavaClock

JavaClock is a Java application that displays the current time and a countdown to the next ice age. The application alternates between showing the current time and the ice age countdown every 10 seconds. The ice age countdown continues even if the program is closed and restarted.

## Features

- Fullscreen display of the current time and ice age countdown.
- Alternates between current time and ice age countdown every 10 seconds.
- Ice age countdown persists across program restarts.
- Exit the application by pressing the `SPACE` key.

## Installation

1. Clone the repository:
    ```sh
    git clone git@github.com:MimoAlexer/JavaClock.git
    cd JavaClock
    ```

2. Compile the Java program:
    ```sh
    javac src/Main.java
    ```

3. Run the program:
    ```sh
    java -cp src Main
    ```

## Usage

- The application will start in fullscreen mode.
- It will display the current time and switch to the ice age countdown every 10 seconds.
- Press the `SPACE` key to exit the application.

## File Structure

- `src/Main.java`: The main Java file containing the application logic.
- `ice_age_start_time.txt`: A file that stores the start time for the ice age countdown.

## Contributing

Contributions are welcome! Please fork the repository and submit a pull request.

## License

This project is licensed under the MIT License. See the `LICENSE` file for details.
