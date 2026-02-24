package steps;

import io.cucumber.java.en.*;
import static org.junit.jupiter.api.Assertions.*;
import states.*;
import states.timer.*;
import states.stopwatch.*; // Added to access stopwatch static values

public class TimerSteps {
	private Context context;

	@Given("the chronometer is initialized in the default state")
	public void setup() {
		context = new Context();
		AbstractTimer.resetInitialValues();
		AbstractStopwatch.resetInitialValues();
	}

	@When("I press the {string} button")
	@When("I press the {string} button to enter SetTimer")
	@When("I press the {string} button again")
	public void pressButton(String button) {
		switch (button.toLowerCase()) {
			case "left" -> context.left();
			case "right" -> context.right();
			case "up" -> context.up();
			case "tick" -> context.tick();
		}
	}

	// FIX 1: Handle "I press the 'tick' button 2 times"
	@When("I press the {string} button {int} times")
	public void pressButtonMultiple(String button, Integer times) {
		for (int i = 0; i < times; i++) {
			pressButton(button);
		}
	}

	@When("I set the timer to {int} second")
	public void setTimerToValue(Integer seconds) {
		context.right();
		for (int i = 0; i < seconds; i++)
			context.tick();
		context.right();
	}

	@When("the timer finishes counting down")
	public void timerFinishes() {
		context.up();
		context.tick();
	}

	@Then("the mode should be {string}")
	public void checkMode(String mode) {
		assertEquals(mode, context.currentState.getMode().toString());
	}

	@Then("the current state should be {string}")
	@Then("the current state should still be {string}")
	public void checkState(String stateName) {
		switch (stateName) {
			case "IdleTimer" -> assertSame(IdleTimer.Instance(), context.currentState);
			case "SetTimer" -> assertSame(SetTimer.Instance(), context.currentState);
			case "PausedTimer" -> assertSame(PausedTimer.Instance(), context.currentState);
			case "RunningTimer" -> assertSame(RunningTimer.Instance(), context.currentState);
			case "RingingTimer" -> assertSame(RingingTimer.Instance(), context.currentState);
			case "ResetStopwatch" -> assertSame(ResetStopwatch.Instance(), context.currentState);
			case "RunningStopwatch" -> assertSame(RunningStopwatch.Instance(), context.currentState);
			case "LaptimeStopwatch" -> assertSame(LaptimeStopwatch.Instance(), context.currentState);
		}
	}

	@Then("the timer value should be {int}")
	public void checkTimerValue(int value) {
		assertEquals(value, AbstractTimer.getTimer());
	}

	@Then("the memory timer should be {int}")
	public void checkMemTimerValue(int value) {
		assertEquals(value, AbstractTimer.getMemTimer());
	}

	// FIX 2: Handle "the stopwatch laptime should be 1"
	@Then("the stopwatch laptime should be {int}")
	public void checkStopwatchLaptime(Integer value) {
		assertEquals(value, AbstractStopwatch.getLapTime());
	}

	@Then("I should return to the original timer state")
	public void checkReturnToOriginal() {
		assertEquals(Mode.timer, context.currentState.getMode());
	}

	@Then("the alarm should be ringing")
	public void isRinging() {
		assertTrue(AbstractTimer.getRing());
	}

	@Then("the alarm should stop ringing")
	public void isNotRinging() {
		assertFalse(AbstractTimer.getRing());
	}
}
