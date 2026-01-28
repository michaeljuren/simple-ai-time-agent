# Hello Time Agent (Java + Spring Boot)

Small Java project demonstrating a Google Agent Development Kit (ADK) agent that returns the current time for a specified city.

## Overview
- Agent implementation: `HelloTimeAgent` (tool: `getCurrentTime`)
- CLI runner: `AgentCLIRunner` (interactive prompt)
- Spring Boot entrypoint: `AgentApplication`
- Based on the Google Agent Development Kit for Java

## Prerequisites
- Java 17 or later
- Maven 3.9 or later
- A valid Google API key

## Environment (API key)
This project includes an `env.bat` helper (located at the project root of the `agent` module):

```bat
@echo off
set GOOGLE_API_KEY=YourGoogleAPIKey
```

You can either:
- Edit `agent\env.bat` and replace `YourGoogleAPIKey` with your key, then run `call env.bat` in the same shell before starting the app;
- Or set the `GOOGLE_API_KEY` environment variable globally in your OS; or
- Set the `GOOGLE_API_KEY` in your IDE's Run/Debug configuration for the specific run target.

Note: `env.bat` is a helper script and is not automatically loaded by IDE run configurations when you run a single class.

## Build
From the `agent` directory:

```powershell
cd agent
mvn clean package
```

## Run
- Spring Boot application (development):

```powershell
cd agent
mvn spring-boot:run
# or run the packaged jar
java -jar target/<artifact>-<version>.jar
```

- CLI interactive runner (`AgentCLIRunner`):

If you want the interactive CLI, run the `AgentCLIRunner` main class. Make sure `GOOGLE_API_KEY` is available to the process (see Environment section).

From Maven:

```powershell
cd agent
mvn exec:java -Dexec.mainClass="com.michaelj.agent.AgentCLIRunner"
```

Or run from your IDE by launching `com.michaelj.agent.AgentCLIRunner`.

## IntelliJ / Run Configuration note
If you run `AgentCLIRunner` from IntelliJ and the agent can't find the API key, open **Run > Edit Configurations...**, select the configuration for `AgentCLIRunner`, and add an environment variable `GOOGLE_API_KEY=YourGoogleAPIKey` (or point the working directory to where `env.bat` is and `call` it from a wrapper script). The IDE does not automatically load `env.bat` when launching a single class.

## Troubleshooting
- "Missing API key" or authorization errors: Ensure `GOOGLE_API_KEY` is present in the environment for the running process.
- If you modified Java or Maven settings, ensure your Java version is 17+ and Maven is 3.9+.

## Notes
- `HelloTimeAgent` maps common city names to timezones and returns a formatted current time.
- This project follows the Google Agent Development Kit for Java patterns and uses `gemini-2.5-flash` as the configured model in the agent definition.

## Where to look
- Agent code: `src/main/java/com/michaelj/agent/HelloTimeAgent.java`
- CLI runner: `src/main/java/com/michaelj/agent/AgentCLIRunner.java`
- Spring Boot app: `src/main/java/com/michaelj/agent/AgentApplication.java`
- env helper: `env.bat`


---
Generated on 2026-01-28.
