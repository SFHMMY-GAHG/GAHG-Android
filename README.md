GAHG-Android
============

Android application implemented for SFHMMY7 and the workshop "Groovy Android and the Holy Grails"

Instructions
------------

- Adding comments for every auto-generated function in MainActivity
- RelativeLayout changes
	* Button (add ID, w=match_parent, layout_below, text(String-->execute))
	* EditText (w=match_parent, add ID, gravity=center_horizontal, hint)
	* TextView (add ID, w=match_parent, gravity=center, h=match_parent)

----Checkpoint----

- MainActivity again...
	* onCreate()
		- find all views (and declare them as variables)
		- set listener for button (make activity implement listener and override OnClick function)
	* onClick()
		- Log.d and Toast and run for first time
		- switch on view pressed

----Checkpoint----
		
	* create new function Execute()
		- Load text from editText
		- check if string is not null
		- set textView with string
		- show problem with Thread.sleep(2000)
	
----Checkpoint - Dimitris----
		
	* AsyncTask
		- create and copy all the code
		
- Create new PreferenceActivity --> SettingsActivity + ANDROID MANIFEST
	* create directory xml and new resource --> settings_main --> PreferenceScreen/PreferenceCategory/EditTextPreference (key, title, summary)
	* addPreferenceFromRecourse (settings_main)

----Checkpoint----
	
- In MainActivity 
	* In Execute()
		- SharedPreferences
		- check if username is not null
		- concatenate username to string from editText

----Checkpoint----

	* Add remote_settings to PreferenceActivity
		- Server IP (key, title, summary)

----Checkpoint----

- Download AQuery.jar from link https://android-query.googlecode.com/files/android-query.0.26.7.jar
- Copy it to libs and Re-sync gradle
- MainActivity
	* Declare AQuery
	* Initialize in OnCreate
	* Create serverRequest
	* Create response
	* Don't use our AsyncTask, instead call serverRequest()

EXTRAS
----------
- Android Icon
- Function for SharedPreference (maybe application)
