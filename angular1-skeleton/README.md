# angular-skeleton

[![Build Status](https://travis-ci.org/chxj1992/angular-skeleton.svg?branch=master)](https://travis-ci.org/chxj1992/angular-skeleton) 

A skeleton project of angularjs with requirejs, and there's also an example to setup karma test with angular & requirejs.

You can see a [demo](http://angularjs.chxj.name/) here.

### Getting Started

There are two options to run this project:

+ locally (require: [Nodejs](https://nodejs.org/) installed):

        npm start

+ in docker (require: [Docker](https://docs.docker.com/installation/#installation) installed):
    
        docker build -t angular-skeleton .
        docker run -d -p 8000:8000 angular-skeleton:latest

Then open your browser and visit `http://localhost:8000`
    

### Unit test

    npm test
    // or only run test once
    npm test-single-run

All tests are written in [jasmine](http://jasmine.github.io/). 

### E2E test

    npm start
    npm run protractor
    
Learn more about [Protractor](http://angular.github.io/protractor/#/).

    
### Problems Shooting

+ If you see `cannot run in wd` when you run the above commands, just try this instead:

    npm start --unsafe-perm
    
+ protractor is going to download the chrome webdriver from  [google storage](http://chromedriver.storage.googleapis.com/index.html) at the first time. If you are not able to access google, you have to setup a proxy before you run `npm run protractor`. Or you can manually download the webdriver from some where else and save it to `node_modules/protractor/selenium/`.
