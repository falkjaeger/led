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
var stopIt = function () {
    var xhtmlRequest = new XMLHttpRequest();
    xhtmlRequest.open('POST', 'stopIt', true);
    xhtmlRequest.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
    xhtmlRequest.send();
};

var modify = function (name) {
    console.log(name);
    console.log(document.getElementById(name).value);
    var xhtmlRequest = new XMLHttpRequest();
    xhtmlRequest.open('POST', 'modify', true);
    xhtmlRequest.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
    xhtmlRequest.send('led=' + name + '&timeout=' + document.getElementById(name).value);
}