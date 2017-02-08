/**
 * Created by falkj on 24.12.2016.
 */

var toggleLed = function (led) {
    var xhtmlRequest = new XMLHttpRequest();
    xhtmlRequest.open('POST', 'toggleLed', true);
    xhtmlRequest.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
    xhtmlRequest.send('led=' + led);
};

var allOff = function () {
    var xhtmlRequest = new XMLHttpRequest();
    xhtmlRequest.open('POST', 'allOff', true);
    xhtmlRequest.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
    xhtmlRequest.send();
};
var modify = function () {
    allOff();
    var leds = [
        {
            led: 'RED',
            timeout: document.getElementById('RED').value
        },
        {
            led: 'GREEN',
            timeout: document.getElementById('GREEN').value
        }, {
            led: 'BLUE',
            timeout: document.getElementById('BLUE').value
        }];

    for (var i = 0; i < leds.length; i++) {
        if (leds[i].timeout > 0) {
            var xhtmlRequest = new XMLHttpRequest();
            xhtmlRequest.open('POST', 'modify', true);
            xhtmlRequest.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
            xhtmlRequest.send('led=' + leds[i].led + '&timeout=' + leds[i].timeout);
        }
    }


};