Feature: Timer and Stopwatch Integration
This feature tests the full user journey of the chronometer.

	Scenario: Complete User Journey - Configure Timer, use Stopwatch, and finish Timer
  Given the chronometer is initialized in the default state
  
  # Configure Timer
  When I press the "right" button
  And I press the "tick" button 2 times
  Then the current state should be "SetTimer"
  And the memory timer should be 2
  
  # Start and Pause Timer
  When I press the "right" button
  And I press the "tick" button
  And I press the "up" button
  And I press the "tick" button
  And I press the "up" button
  Then the current state should be "PausedTimer"
  And the timer value should be 1
  
  # Use Stopwatch
  When I press the "left" button
  And I press the "tick" button
  Then the current state should be "ResetStopwatch"
  When I press the "up" button
  And I press the "tick" button
  Then the current state should be "RunningStopwatch"
  When I press the "up" button
  And I press the "tick" button
  Then the current state should be "LaptimeStopwatch"
  And the stopwatch laptime should be 1
  
  # Return to Timer via History and Finish
  When I press the "left" button
  And I press the "tick" button
  Then the current state should be "PausedTimer"
  When I press the "up" button
  And I press the "tick" button
  Then the current state should be "RingingTimer"
  When I press the "right" button
  And I press the "tick" button
  Then the current state should be "IdleTimer"
