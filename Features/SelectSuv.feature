#Author: your.email@your.domain.com
#Keywords Summary :
#Feature: List of scenarios.
#Scenario: Business rule through list of steps with arguments.
#Given: Some precondition step
#When: Some key actions
#Then: To observe outcomes or validation
#And,But: To enumerate more Given,When,Then steps
#Scenario Outline: List of steps for data-driven as an Examples and <placeholder>
#Examples: Container for s table
#Background: List of steps run before each of the scenarios
#""" (Doc Strings)
#| (Data Tables)
#@ (Tags/Labels):To group Scenarios
#<> (placeholder)
#""
## (Comments)
#Sample Feature Definition Template
Feature: Select SUV

@Sanity
	Scenario: Successfully display only SUVs from the result
		Given user launch browser
		And opens the url "https://www.makemytrip.com/"
		When user clicks on cab weblink
		And user enter data for cab search
		When user sort the results by selecting suv
		Then user will get suv available as per criteria