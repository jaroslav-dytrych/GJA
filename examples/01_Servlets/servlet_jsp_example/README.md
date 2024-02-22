##### Number Guesser Game with Cookies (/servlet_example/ExampleServlet)

- For deployment see [Section on .war deployment](#deploying-war-files-to-GlassFish-linux-but-it-should-work-on-windows-too).
- The Servlet example is a Random Number Guessing game. The class implements `HTTP` requests and utilizes `Cookies` for primitive session keeping.
- You can test the Example Servlet via a web browser or "API-testing" program like [Postman](https://www.postman.com/) or `cURL` (manipulating raw HTTP requests sent to e.g.<http://localhost:8080/servlet_jsp_example/ExampleServlet>) - You need to use something like `Postman` to test DELETE and PUT methods - as they can't be called from `HTML` page.
- You can test `GET`, `POST`, `PUT`, `DELETE`  `HTTP` requests on the Servlet.
- By calling `PUT` manually you restart the game. By calling `DELETE` you delete the Cookies and Restart the Game (deletes your username, high score etc.) - this will be only available through `cURL` and/or `Postman` as they have different sessions than your browser.
- This example, showing basic capabilities of Servlets is also a deterrent example of why it is better to use something like `JSP` for `HTML` rendering.

### Javadoc Documentation

Automatically generated Javadoc documentation can be found in the `doc/` folder in each project subfolder.
Open the `index.html` in each documentation to see the Javadoc.

### Prerequisites

 The project was developed in [Apache Netbeans 16](https://netbeans.apache.org/download/index.html) with [Eclipse Temurin JDK 17](https://adoptium.net/temurin/releases/?version=17) and [Eclipse GlassFish 7](https://projects.eclipse.org/projects/ee4j.GlassFish/releases/7.0.0) (`Jakarta EE 10 API`). Other prerequisite is that the user is somehow familiar with using Netbeans and deploying Java Web Applications onto the server. If not, user can read the provided documentation.

### Precompiled .war files

- For people, that just want to try the project, but don't want to build it themselves, they can download compiled `.war/.jar` files from the project's github <https://github.com/bartak-v/gja>. The `.war` file
for each project is in the subfolder of the project.

### Installation and run

- Install `GlassFish 7`
- Run `GlassFish` (either manually or through Netbeans)
- Deploy the compiled `.war` file onto the `GlassFish` server (defaultly through [http://localhost:4848](http://localhost:4848))
- You can also build the project yourself through standard practice in `Netbeans` (or manually using `Maven`).

### Building and deploying the projects with Netbeans 16 and GlassFish 7

#### Environment Setup

- First, install [Eclipse Temurin JDK 17](https://adoptium.net/temurin/releases/?version=17) and check that the `JAVA_HOME` is set correctly to the proper path, to where you have your JDK installed. `JAVA_HOME` should be set to `...example_jdk_path/`. Environment variable setup is out of scope of this project and we recommend `Google` if you have trouble with this (if it does not setup automatically when installing etc.).
- Install [Apache Netbeans 16](https://netbeans.apache.org/download/index.html).
- To correctly deploy the examples, download and install [GlassFish 7](https://GlassFish.java.net/download.html) on your computer. Save it in some User-owned folder that you have permissions to.
- To set-up `Netbeans` to use the `GlassFish 7` server: Open `Netbeans 16`. Click on `Tools -> Servers` in the top bar.
- Click `Add Server...`. Choose `GlassFish Server` and `Next`.
- Set the `Installation Location` to the folder where you got your `Glassfish 7` downloaded. Select `Local Domain`.
- DO NOT ACCEPT THE TERMS OF SERVICE AND DO NOT DOWNLOAD `GlassFish 6.2.5`, just click `Next`.
- `Netbeans` will pretend, that your installation is version `6.2.5` but it will correctly work and run the version `7.0.0`.
- In the next step set you can leave the `Defaults` and click `Finish` etc.
- In the Server tab you should have set `Java Platform` to `JDK 17` for the server.
- You should now have correctly set-up `Netbeans` to use `GlassFish 7.0.0`

#### Building and deploying the example projects in Netbeans 16

- Start `Netbeans` and click on the `File` menu in the top left corner.
- Select `Open Project` from the drop-down menu.
- In the file browser window that appears, navigate to the location where your project is stored and select the project folder.
- Click the `Open` button.
- Click on the `Build menu` in the top menu bar.
- Select `Clean and Build Project` from the drop-down menu.
- If the build is successful, you should see a message indicating that the build was successful. If there are any errors, they will be displayed in the output window at the bottom of the Netbeans window.
- You can now click the `Play` button or press `F6` or go to `Run -> Run Project`, if you setup `Netbeans` correctly, web browser should start with the Project running.
- You should also be able to manage the server from the `Services -> Servers -> GlassFish Server` (start, stop, open admin GUI etc.).
- Follow the instructions for each project (as they can be different than this) to successfully deploy and run the example.

#### Deploying .war files to GlassFish manually (Linux but it should work on Windows too)

- You can also deploy the project manually. After Building the project, the resulting `.war` file should be in the `target/` folder in the root of the project.

- To correctly deploy the examples, download and install `GlassFish 7` on your computer. You can download it from the following link:
    <https://GlassFish.java.net/download.html>

- Once `GlassFish` is installed, start the `domain`. You can start the `domain` by running the following command (in the `GlassFish` installation folder go to `GlassFish/bin/`) and run:
    `./asadmin start-domain` - for this to work you have to have correctly set the `JAVA_HOME` environment variable to where you have your JDK installed.

- Next, open a web browser and navigate to the `GlassFish Administration Console` at the following URL:
    <http://localhost:4848/>

- If it wants login credentials, either leave them empty (username `admin` and empty password or you can follow <https://docs.oracle.com/cd/E18930_01/html/821-2416/giubb.html> or it should be `admin admin`...)

- Log in to the `Administration Console` using the default username.

- In the left navigation menu, click on the `Applications` link.

- Click on the `Deploy...` button.

- In the `Deploy Applications` screen, click on the `Choose File` button and select the example `.war` file that you want to deploy.

- In the next screen, you can specify deployment options such as the context root and the target server (you can leave the defaults). Make any necessary changes and click on the `Finish` button to deploy the `.war` file.

- Click on the `Applications` on the left again and `Launch` the specified Application. It should show you the links, but we recommend to change the `Context Root` in the application to something like `/servlet_jsp_example` (instead of `/servlet_jsp_example-14374286702991946667.0` etc.) or just leave it and use it as the root of the examples.

- The `.war` file will now be deployed to GlassFish and should be accessible at the specified context root. You can check the URLs we specify in the examples section.
