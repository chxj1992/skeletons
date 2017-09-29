exports.config = {
    allScriptsTimeout: 11000,

    seleniumAddress: 'http://localhost:8000/',
    directConnect: true,

    specs: [
        'e2e/*.js'
    ],

    capabilities: {
        'browserName': 'firefox'
    },

    chromeOnly: true,

    baseUrl: 'http://localhost:8000/',

    framework: 'jasmine',

    jasmineNodeOpts: {
        defaultTimeoutInterval: 30000
    }
};
