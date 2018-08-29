# Feature management

Simple scala play frontend demonstrating feature switching

## How to start
```sbtshell
sbt -Dfeatures.app=true run
```

or

```sbtshell
sbt stage
cd target/universal/stage/bin
./feature-management -Dfeatures.app=true
```

## Routes
**GET /sample-view**

Render html page containing dummy link to register/login and a set of contact details

**POST /set-feature/:name/state/:boolean**

Turns a feature on or off. Available features include
- Login: Toggles html in the sample view
- contact-details: Toggles html in the sample view
- app: Toggles whether the service is available or not