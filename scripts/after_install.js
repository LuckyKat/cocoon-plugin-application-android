#!/usr/bin/env node

var fs = require('fs');
var path = require('path');

module.exports = function(context) {
    if (context.opts.cordova.platforms.indexOf('android') <= -1)
        return;

    var manifest_xml = path.join(context.opts.projectRoot, 'platforms', 'android','AndroidManifest.xml');
    if (!fs.existsSync(manifest_xml)) {
        // try cordova >=7 path
        manifest_xml = path.join(context.opts.projectRoot, 'platforms', 'android', 'app', 'src', 'main', 'AndroidManifest.xml');
    }
    var et = context.requireCordovaModule('elementtree');

    var data = fs.readFileSync(manifest_xml).toString();
    var etree = et.parse(data);

    // Add Multidex application 
    var applications = etree.findall('./application');
    applications[0].attrib['android:name'] = "com.ludei.CocoonApp";

    data = etree.write({'indent': 4});
    fs.writeFileSync(manifest_xml, data);
};