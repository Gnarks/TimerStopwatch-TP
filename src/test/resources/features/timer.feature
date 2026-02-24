Feature: Timer State and Alarm Logic
  As a user of the Chronometer
  I want the timer to manage its states and alarm correctly
  So that I can set and use the timer reliably

  Background:
    Given the chronometer is initialized in the default state 

  Scenario: Initial state should be Idle Timer with zeroed values
    Then the mode should be "timer" 
    And the current state should be "IdleTimer" 
    And the timer value should be 0 

  Scenario: Setting the timer increases the memory timer
    When I press the "right" button to enter SetTimer 
    And I press the "tick" button 
    And I press the "up" button 
    Then the memory timer should be 6 
    And the current state should still be "SetTimer" 

  Scenario: Timer rings when it reaches zero
    When I set the timer to 1 second 
    And the timer finishes counting down 
    Then the alarm should be ringing 
    When I press the "right" button 
    Then the alarm should stop ringing 

  Scenario: Toggling between Timer and Stopwatch preserves history
    When I press the "left" button 
    Then the current state should be "AbstractStopwatch" 
    When I press the "left" button again 
    Then I should return to the original timer state
