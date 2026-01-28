package com.michaelj.agent;

import com.google.adk.agents.BaseAgent;
import com.google.adk.agents.LlmAgent;
import com.google.adk.tools.Annotations.Schema;
import com.google.adk.tools.FunctionTool;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;

public class HelloTimeAgent {
    public static BaseAgent ROOT_AGENT = initAgent();

    private static BaseAgent initAgent() {
        return LlmAgent.builder()
                .name("hello-time-agent")
                .description("Tells the current time in a specified city")
                .instruction("""
                You are a helpful assistant that tells the current time in a city.
                Use the 'getCurrentTime' tool for this purpose.
                """)
                .model("gemini-2.5-flash")
                .tools(FunctionTool.create(HelloTimeAgent.class, "getCurrentTime"))
                .build();
    }

    /** tool implementation */
    @Schema(description = "Get the current time for a given city")
    public static Map<String, String> getCurrentTime(
            @Schema(name = "city", description = "Name of the city to get the time for") String city) {

        try {
            // Map cities to timezones
            Map<String, String> cityToZone = Map.ofEntries(
                    Map.entry("new york", "America/New_York"),
                    Map.entry("los angeles", "America/Los_Angeles"),
                    Map.entry("chicago", "America/Chicago"),
                    Map.entry("london", "Europe/London"),
                    Map.entry("paris", "Europe/Paris"),
                    Map.entry("tokyo", "Asia/Tokyo"),
                    Map.entry("sydney", "Australia/Sydney"),
                    Map.entry("dubai", "Asia/Dubai"),
                    Map.entry("singapore", "Asia/Singapore")
            );

            String normalizedCity = city.toLowerCase().trim();
            String zoneIdString = cityToZone.getOrDefault(normalizedCity, "UTC");
            ZoneId zoneId = ZoneId.of(zoneIdString);

            // Get current time
            ZonedDateTime now = ZonedDateTime.now(zoneId);
            String timeString = now.format(DateTimeFormatter.ofPattern("h:mm a"));

            return Map.of(
                    "city", city,
                    "time", timeString
            );
        } catch (Exception e) {
            return Map.of(
                    "city", city,
                    "error", "Could not find timezone for " + city
            );
        }
    }
}
