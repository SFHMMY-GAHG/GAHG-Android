GAHG-Android
============

Android application implemented for SFHMMY7 and the workshop "Groovy Android and the Holy Grails"

Instructions
------------

- Adding comments for every auto-generated function in MainActivity
- RelativeLayout changes
	* TextView (add ID, w=match_parent, gravity=center, h=match_parent)
	* Button (add ID, w=match_parent, layout_below, text(String-->execute))
	* EditText (w=match_parent, add ID, gravity=center_horizontal, hint)
- MainActivity again...
	* onCreate()
		- find all views (and declare them as variables)
		- set listener for button (make activity implement listener and override OnClick function)
	* onClick()
		- Log.d and Toast and run for first time
		- switch on view pressed
	* create new function Execute()
		- Load text from editText
		- check if string is not null
		- set textView with string
		- show problem with Thread.sleep(2000)
- Create new PreferenceActivity --> SettingsActivity + ANDROID MANIFEST
	* create directory xml and new resource --> settings_main --> PreferenceScreen/PreferenceCategory/EditTextPreference (key, title, summary)
	* addPreferenceFromRecourse (settings_main)
- In MainActivity 
	* In Execute()
		- SharedPreferences
		- check if username is not null
		- concatenate username to string from editText
	* AsyncTask
		- create and copy all the code
	* Add remote_settings to PreferenceActivity
		- Server IP (key, title, summary)

After Break
--------------
- Download AQuery.jar from link http://code.google.com/p/android-query/downloads/list
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
