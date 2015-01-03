// Helper and handler functions.

// Google search settings, load and other related functions. -----------------

var customSearchControl;

function homeGCSEOnLoad() {
    var gcseSearchContentDiv = 'gcseSearchContent'; // Change this if you changed div name
    var customSearchOptions = {};
    var imageSearchOptions = {};
    var customSearchDrawOptions = new google.search.DrawOptions();
    customSearchOptions['enableImageSearch'] = true;
    imageSearchOptions['layout'] = 'google.search.ImageSearch.LAYOUT_CLASSIC';
    // Create a custom search control that uses a CSE
    customSearchControl = new google.search.CustomSearchControl('003170758506996544683:fvmekwqznq0', customSearchOptions);
    // customSearchControl = new google.search.CustomSearchControl('003170758506996544683:lymgiivhtys', customSearchOptions);
    // Customise how it looks and where the links open
    customSearchDrawOptions.enableSearchResultsOnly();
    customSearchControl.setLinkTarget(google.search.Search.LINK_TARGET_SELF);
    customSearchControl.draw(gcseSearchContentDiv, customSearchDrawOptions);
}

function loadHomeGCSE(gcseSearchContentDiv) {
    // Load google engine.
    google.load('search', '1');
    google.setOnLoadCallback(homeGCSEOnLoad);
}

function reconstructSearchQuery(searchQueryTextID, searchQuerySourceID, searchQueryModuleID) {

    var searchQueryText = document.getElementById(searchQueryTextID).value;
    var searchQuerySource = document.getElementById(searchQuerySourceID);
    var searchQueryModule = document.getElementById(searchQueryModuleID);
    var currentLanguageIndex = homeSiteConfig.availableLanguages.indexOf(homeSiteConfig.siteLanguage);

    var firstModuleSelectionAdded = false;
    var firstSourceSelectionAdded = false;
    var searchQueryModuleIterator = 0;
    var searchQuerySourceIterator = 0;

    if (searchQueryModule != null) {
        if (searchQueryModule.options[0].selected != true) { // If not ALL selected
            for (searchQueryModuleIterator = 0; searchQueryModuleIterator < searchQueryModule.options.length; searchQueryModuleIterator++) {
                if (searchQueryModule.options[searchQueryModuleIterator].selected == true) {
                    if (firstModuleSelectionAdded == false) {
                        searchQueryText = searchQueryText + ' site:' + homeSiteConfig.siteModules[searchQueryModule.options[searchQueryModuleIterator].value].baseURL[currentLanguageIndex];
                        firstModuleSelectionAdded = true;
                    }
                    else {
                        searchQueryText = searchQueryText + ' OR site:' + homeSiteConfig.siteModules[searchQueryModule.options[searchQueryModuleIterator].value].baseURL[currentLanguageIndex];
                    }
                }
            }
        }
        else {
            if (firstModuleSelectionAdded == false) {
                searchQueryText = searchQueryText + ' site:' + homeSiteConfig.siteModules[searchQueryModule.options[0].value].baseURL[currentLanguageIndex];
                firstModuleSelectionAdded = true;
            }
            else {
                searchQueryText = searchQueryText + ' OR site:' + homeSiteConfig.siteModules[searchQueryModule.options[0].value].baseURL[currentLanguageIndex];
            }
        }
    }

    if (searchQuerySource != null) {
        if (searchQuerySource.options[0].selected != true) { // If not ALL selected
            for (searchQuerySourceIterator = 0; searchQuerySourceIterator < searchQuerySource.options.length; searchQuerySourceIterator++) {
                if (searchQuerySource.options[searchQuerySourceIterator].selected == true) {
                    if ((firstSourceSelectionAdded == false) && (firstModuleSelectionAdded == false)) {
                        searchQueryText = searchQueryText + ' site:' + homeSiteConfig.siteSources[searchQuerySource.options[searchQuerySourceIterator].value].siteURL[currentLanguageIndex];
                        firstSourceSelectionAdded = true;
                    }
                    else {
                        searchQueryText = searchQueryText + ' OR site:' + homeSiteConfig.siteSources[searchQuerySource.options[searchQuerySourceIterator].value].siteURL[currentLanguageIndex];
                    }
                }
            }
        }
        else {
            for (searchQuerySourceIterator = 1; searchQuerySourceIterator < searchQuerySource.options.length; searchQuerySourceIterator++) {
                if ((firstSourceSelectionAdded == false) && (firstModuleSelectionAdded == false)) {
                    searchQueryText = searchQueryText + ' site:' + homeSiteConfig.siteSources[searchQuerySource.options[searchQuerySourceIterator].value].siteURL[currentLanguageIndex];
                    firstSourceSelectionAdded = true;
                }
                else {
                    searchQueryText = searchQueryText + ' OR site:' + homeSiteConfig.siteSources[searchQuerySource.options[searchQuerySourceIterator].value].siteURL[currentLanguageIndex];
                }
            }
        }
    }

    // alert(searchQueryText);

    return searchQueryText;
}

function encodeSpecialSearchURL(searchQueryTextID, searchQuerySourceID, searchQueryModuleID) {

    var encodedPartialURL = '';
    var searchQueryText = document.getElementById(searchQueryTextID).value;
    var searchQuerySource = document.getElementById(searchQuerySourceID);
    var searchQueryModule = document.getElementById(searchQueryModuleID);
    var currentLanguageIndex = homeSiteConfig.availableLanguages.indexOf(homeSiteConfig.siteLanguage);
    var firstModuleSelectionAdded = false;
    var firstSourceSelectionAdded = false;
    var searchQueryModuleIterator = 0;
    var searchQuerySourceIterator = 0;
    var noSourceSelected = true;
    var noModuleSelected = true;

    if (searchQueryModule != null) {
        if (searchQueryModule.options[0].selected != true) { // If not ALL selected
            for (searchQueryModuleIterator = 0; searchQueryModuleIterator < searchQueryModule.options.length; searchQueryModuleIterator++) {
                if (searchQueryModule.options[searchQueryModuleIterator].selected == true) {
                    if (firstModuleSelectionAdded == false) {
                        searchQueryText = searchQueryText + '&module=' + searchQueryModuleIterator;
                        firstModuleSelectionAdded = true;
                        noModuleSelected = false;
                    }
                    else {
                        searchQueryText = searchQueryText + ',' + searchQueryModuleIterator;
                    }
                }
            }
            if (noModuleSelected === true) {
                // Place holder for handling case that no source selected.
            }
        }
        else {
            searchQueryText = searchQueryText + '&module=0';
        }
    }

    if (searchQuerySource != null) {
        if (searchQuerySource.options[0].selected != true) { // If not ALL selected
            for (searchQuerySourceIterator = 0; searchQuerySourceIterator < searchQuerySource.options.length; searchQuerySourceIterator++) {
                if (searchQuerySource.options[searchQuerySourceIterator].selected == true) {
                    if (firstSourceSelectionAdded == false) {
                        searchQueryText = searchQueryText + '&source=' + searchQuerySourceIterator;
                        firstSourceSelectionAdded = true;
                        noSourceSelected = false;
                    }
                    else {
                        searchQueryText = searchQueryText + ',' + searchQuerySourceIterator;
                    }
                }
            }
            if ( ( noSourceSelected === true ) && ( homeSiteConfig.siteModulesProperties.isModuleList === false ) )
                searchQueryText = searchQueryText + '&source=' + homeSiteConfig.siteSourcesProperties.defaultNoSelectionSource;
        }
        else {
            searchQueryText = searchQueryText + '&source=0';
        }
    }

    return searchQueryText;
}

function decodeSpecialSearchURL(specialPartialSearchURL, searchQuerySourceID, searchQueryModuleID) {

    var currentLanguageIndex = homeSiteConfig.availableLanguages.indexOf(homeSiteConfig.siteLanguage);
    var searchQuerySource = document.getElementById(searchQuerySourceID);
    var searchQueryModule = document.getElementById(searchQueryModuleID);
    var firstModuleSelectionAdded = false;
    var firstSourceSelectionAdded = false;
    var searchQueryModuleIterator = 0;
    var searchQuerySourceIterator = 0;
    var moduleList = null;
    var sourceList = null;

    var searchQueryURLparts = specialPartialSearchURL.split('&');

    var searchQueryText = searchQueryURLparts[0];

    var seasrchQueryParts = [searchQueryURLparts[1].split('='), searchQueryURLparts[2].split('=')];

    if (searchQueryModuleID != null) {
        if ((seasrchQueryParts[0][0] == 'module') || (seasrchQueryParts[1][0] == 'module')) { // Parse module part
            if (seasrchQueryParts[0][0] == 'module')
                moduleList = seasrchQueryParts[0][1].split(',');
            else
                moduleList = seasrchQueryParts[1][1].split(',');
            // Reset all selections
            for (searchQueryModuleIterator = 0; searchQueryModuleIterator < searchQueryModule.options.length; searchQueryModuleIterator++)
                searchQueryModule.options[searchQueryModuleIterator].selected = false;
            if (moduleList[0] == 0)
                searchQueryModule.options[0].selected = true;
            else
                for (var moduleListIterator = 0; moduleListIterator < moduleList.length; moduleListIterator++)
                    searchQueryModule.options[moduleList[moduleListIterator]].selected = true;
        }
        $("#" + searchQueryModuleID).dropdownchecklist("refresh");
    }

    if (searchQuerySource != null) {
        if ((seasrchQueryParts[0][0] == 'source') || (seasrchQueryParts[1][0] == 'source')) { // parse source part
            if (seasrchQueryParts[0][0] == 'source')
                sourceList = seasrchQueryParts[0][1].split(',');
            else
                sourceList = seasrchQueryParts[1][1].split(',');
            // Reset all selections.
            for (searchQuerySourceIterator = 0; searchQuerySourceIterator < searchQuerySource.options.length; searchQuerySourceIterator++)
                searchQuerySource.options[searchQuerySourceIterator].selected = false;
            if ( (sourceList[0] == 0) ) // ALL
                searchQuerySource.options[0].selected = true;
            else
                for (var sourceListIterator = 0; sourceListIterator < sourceList.length; sourceListIterator++)
                    searchQuerySource.options[sourceList[sourceListIterator]].selected = true;
        }
        $("#" + searchQuerySourceID).dropdownchecklist("refresh");
    }

    return searchQueryText;
}

function decodeSpecialSearchURL2(specialPartialSearchURL, searchQuerySourceID, searchQueryModuleID) {

    var currentLanguageIndex = homeSiteConfig.availableLanguages.indexOf(homeSiteConfig.siteLanguage);
    var searchQuerySource = document.getElementById(searchQuerySourceID);
    var searchQueryModule = document.getElementById(searchQueryModuleID);
    var firstModuleSelectionAdded = false;
    var firstSourceSelectionAdded = false;
    var searchQueryModuleIterator = 0;
    var searchQuerySourceIterator = 0;
    var moduleList = null;
    var sourceList = null;

    var searchQueryURLparts = specialPartialSearchURL.split('&');

    var searchQueryText = searchQueryURLparts[0]; // At least the query text should exist in the search URL

    var seasrchQueryParts; // For the rest

    //if (homeSiteConfig.siteModulesProperties.isModuleList == true) {

    if (searchQueryURLparts.length > 1) {
        if (searchQueryURLparts.length == 3) {
            seasrchQueryParts = [searchQueryURLparts[1].split('='), searchQueryURLparts[2].split('=')];
            if ((seasrchQueryParts[0][0] == 'module') || (seasrchQueryParts[1][0] == 'module')) { // Parse module part
                if (seasrchQueryParts[0][0] == 'module')
                    moduleList = seasrchQueryParts[0][1].split(',');
                else
                    moduleList = seasrchQueryParts[1][1].split(',');
            }
            if ((seasrchQueryParts[0][0] == 'source') || (seasrchQueryParts[1][0] == 'source')) { // parse source part
                if (seasrchQueryParts[0][0] == 'source')
                    sourceList = seasrchQueryParts[0][1].split(',');
                else
                    sourceList = seasrchQueryParts[1][1].split(',');
            }
        }
        else {
            seasrchQueryParts = [searchQueryURLparts[1].split('=')];
            if (seasrchQueryParts[0][0] == 'source')
                sourceList = seasrchQueryParts[0][1].split(',');
            else
                moduleList = seasrchQueryParts[0][1].split(',');
        }
    }
    //}

    // Another case : Module list exists + none selected on sources : search only in specific module.
    // Another case : Module list exists + none selected on modules : search only in specific source.
    if ( (searchQueryModuleID != null) && (homeSiteConfig.siteModulesProperties.isModuleList == true) ) {

        // Reset all selections
        for (searchQueryModuleIterator = 0; searchQueryModuleIterator < searchQueryModule.options.length; searchQueryModuleIterator++)
            searchQueryModule.options[searchQueryModuleIterator].selected = false;
        if (moduleList !== null) {
            if (moduleList[0] == 0)
                searchQueryModule.options[0].selected = true;
            else
                for (var moduleListIterator = 0; moduleListIterator < moduleList.length; moduleListIterator++)
                    searchQueryModule.options[moduleList[moduleListIterator]].selected = true;
        }
        else {
            // No modules selected
        }
        $("#" + searchQueryModuleID).dropdownchecklist("refresh");
        if (window.navigator.userAgent.indexOf("MSIE") !== -1) {
            setTimeout(function () { $("#" + searchQueryModuleID).dropdownchecklist("destroy"); $("#" + searchQueryModuleID).dropdownchecklist({ icon: { placement: 'right', toOpen: 'ui-icon-triangle-1-s', toClose: 'ui-icon-triangle-1-n', width: 150 }, firstItemChecksAll: true }); }, 500);
        }
    }

    if (searchQuerySource != null) {

        // Reset all selections.
        for (searchQuerySourceIterator = 0; searchQuerySourceIterator < searchQuerySource.options.length; searchQuerySourceIterator++)
            searchQuerySource.options[searchQuerySourceIterator].selected = false;
        if (sourceList !== null) {
            if ((sourceList[0] == 0)) // ALL
                searchQuerySource.options[0].selected = true;
            else
                for (var sourceListIterator = 0; sourceListIterator < sourceList.length; sourceListIterator++)
                    searchQuerySource.options[sourceList[sourceListIterator]].selected = true;
        }
        else {
            // No sources selected
        }
        $("#" + searchQuerySourceID).dropdownchecklist("refresh");
        if (window.navigator.userAgent.indexOf("MSIE") !== -1) {
            var selectorEmptyText = homeSiteConfig.siteSourcesProperties.defaultNoSelectionSourceText[currentLanguageIndex];
            setTimeout(function () { $("#" + searchQuerySourceID).dropdownchecklist("destroy"); $("#" + searchQuerySourceID).dropdownchecklist({ emptyText: selectorEmptyText, icon: { placement: 'right', toOpen: 'ui-icon-triangle-1-s', toClose: 'ui-icon-triangle-1-n', width: 150 }, firstItemChecksAll: true }); }, 500);
        }
    }

    return searchQueryText;
}


function gcseHomeSearch(searchQueryTextID, searchQuerySourceID, searchQueryModuleID) {

    var searchQueryText = reconstructSearchQuery(searchQueryTextID, searchQuerySourceID, searchQueryModuleID);
    var main_content_div = document.getElementById('main_content');
    main_content_div.style.display = 'none';
    var bottom_toolbar_container_div_ = document.getElementById(homeSiteFooterLinks.divClassID);
    bottom_toolbar_container_div_.style.display = 'none';
    customSearchControl.execute(searchQueryText);
}

function hideDocumentElementsById(docElementsArray) {
    for (var docElementIndex = 0; docElementIndex < docElementsArray.length; docElementIndex++) {
        document.getElementById(docElementsArray[docElementIndex]).style.display = 'none';
    }
}

function gcseHomeSearchHash(searchQueryTextID, searchQuerySourceID, searchQueryModuleID) {
    window.location.assign(decodeURI(homeSiteConfig.baseURL + '/index.html#search/') + encodeSpecialSearchURL(searchQueryTextID, searchQuerySourceID, searchQueryModuleID));
}


function gcseCenterSearch(searchQueryTextID, searchQuerySourceID, searchQueryModuleID) {
    var searchQueryText = reconstructSearchQuery(searchQueryTextID, searchQuerySourceID, searchQueryModuleID);
    Search.Execute(searchQueryText);
}

// Same as in home search experiment
function gcseCenterSearchHash(searchQueryTextID, searchQuerySourceID, searchQueryModuleID) {
    window.parent.location.assign(decodeURI(homeSiteConfig.baseURL + '/index.html#search/') + encodeSpecialSearchURL(searchQueryTextID, searchQuerySourceID, searchQueryModuleID));
}



function loadHomeSelectedLangSite(langSelectorID) {

    var currentLanguageIndex = homeSiteConfig.availableLanguages.indexOf(homeSiteConfig.siteLanguage);
    var selectedLangObj = document.getElementById(langSelectorID);
    var selectedLanguage = selectedLangObj.options[selectedLangObj.selectedIndex].value;
    
    window.location.replace(homeSiteConfig.availableLanguagesSelector.availableLanguagesSites[selectedLanguage].siteURL);
}

function generateHomeHeaderBanner() {

    var languageIterator;
    var currentLanguageIndex = homeSiteConfig.availableLanguages.indexOf(homeSiteConfig.siteLanguage);

    document.write('<div id="helpCenterBanner" style="height:150px; width:960px;">');
    document.write('   <a href="' + homeSiteConfig.baseURL + '" target="_self" style="z-index:-1;">');
    document.write('      <img src="homepage/images/help_center_banner.png" style="margin-left:auto; margin-right:auto; position:absolute; border:none;"/>');
    document.write('   </a>');
    document.write('<div style="float:right; z-index:1;margin:20px 20px 0px 0px;">');
    document.write('<select name="' + homeSiteConfig.availableLanguagesSelector.selectorName + '" ' +
                   'id="' + homeSiteConfig.availableLanguagesSelector.selectorID + '" ' +
                   'style="float:left; position:relative;"'  +
                   'onchange="' + 'loadHomeSelectedLangSite(\'' + homeSiteConfig.availableLanguagesSelector.selectorID + '\')">'
                  );

    for (languageIterator in homeSiteConfig.availableLanguagesSelector.availableLanguagesSites) {
        if (languageIterator.valueOf() == homeSiteConfig.siteLanguage) {
            document.write('<option value="' + languageIterator.valueOf() + '" selected="selected">' +
                           homeSiteConfig.availableLanguagesSelector.availableLanguagesSites[languageIterator].displayItem +
                           '</option>');
        }
        else {
            document.write('<option value="' + languageIterator.valueOf() + '">' +
                           homeSiteConfig.availableLanguagesSelector.availableLanguagesSites[languageIterator].displayItem +
                           '</option>');

        }
    }

    document.write('</select>');
    document.write('</div>');
    document.write('</div>');
}

// Site modules list of links generation

function generateHelpModulesLinks() {
    var moduleIterator;
    var currentLanguageIndex = homeSiteConfig.availableLanguages.indexOf(homeSiteConfig.siteLanguage);

    document.write('<h3 class="' + homeSiteConfig.siteModulesProperties.listHeadingClassID + '">' + 
                    homeSiteConfig.siteModulesProperties.listHeadingText[currentLanguageIndex] + '</h3>');

    for (moduleIterator in homeSiteConfig.siteModules) {
        if (moduleIterator.valueOf() == "All") continue;
        document.write(
            '<div id="siteModuleLinkDivID">' + 
            '<a href="' + homeSiteConfig.siteModules[moduleIterator].moduleURL[currentLanguageIndex] +
            '" class="' + homeSiteConfig.siteModulesProperties.listItemID + '">' + homeSiteConfig.siteModules[moduleIterator].displayItem[currentLanguageIndex] + '<br></a>' +
            '</div>');
    }
}

// Help resources generation

function generateHelpRsourcesLinks() {

    var linkIterator;
    var currentLanguageIndex = homeSiteConfig.availableLanguages.indexOf(homeSiteConfig.siteLanguage);
    
    document.write('<div id="' + homeSiteConfig.homeSiteHelpResources.divClassID + '" class="portalBlockhelpResources">');
    document.write('<h2 id="' + homeSiteConfig.homeSiteHelpResources.headerClassID + '" class="blockHdrhelpResources">' + homeSiteConfig.homeSiteHelpResources.headerDisplayItem[currentLanguageIndex] + '</h2>');

    for (linkIterator in homeSiteConfig.homeSiteHelpResources.helpResources) {
        document.write('<a href="' + homeSiteConfig.homeSiteHelpResources.helpResources[linkIterator].resourceURL[currentLanguageIndex] + '" ' +
                       'class="' + homeSiteConfig.homeSiteHelpResources.itemsClassID + '" ' +
                       'target="' + homeSiteConfig.homeSiteHelpResources.targetID + '">' +
                       homeSiteConfig.homeSiteHelpResources.helpResources[linkIterator].displayItem[currentLanguageIndex] +
                       '<br /></a>');
    }

    document.write('<br>');
    document.write('</div>');
}


// Search component

function generateSearchHeaderWithTR() {
        
    var currentLanguageIndex = homeSiteConfig.availableLanguages.indexOf(homeSiteConfig.siteLanguage);

    document.write('<tr>');
    document.write('<h1 class="myH1">' + homeSiteConfig.searchComponentProperties.headerText[currentLanguageIndex] + '</h1>');
    document.write('</tr>');
}

function generateModuleDropDownList(selectorID) {

    // Create the drop down list for the modules.
    var moduleIterator;
    var currentLanguageIndex = homeSiteConfig.availableLanguages.indexOf(homeSiteConfig.siteLanguage);

    document.write('<select id="' + selectorID + '" multiple>');

    for (moduleIterator in homeSiteConfig.siteModules) {
        document.write('<option value="' + moduleIterator.valueOf() + '">' + homeSiteConfig.siteModules[moduleIterator].displayItem[currentLanguageIndex] + '</option>');
    }
    document.write('</select>');
}
function generateModuleCheckedDropDownList(selectorID) {
    
    if (homeSiteConfig.siteModulesProperties.isModuleList === true) {
        generateModuleDropDownList(selectorID);
        var searchQuerySelectorObj = document.getElementById(selectorID);
        searchQuerySelectorObj.options[0].selected = "selected";
        $("#" + selectorID).dropdownchecklist({ icon: { placement: 'right', toOpen: 'ui-icon-triangle-1-s', toClose: 'ui-icon-triangle-1-n', width: 150 }, firstItemChecksAll: true });
    }
}

function generateModuleDropDownListWithTD(tdIDs, selectorID) {

    var currentLanguageIndex = homeSiteConfig.availableLanguages.indexOf(homeSiteConfig.siteLanguage);

    if (homeSiteConfig.siteModulesProperties.isModuleList === true) {
        document.write('<td id="' + tdIDs[0] + '" title="Creo Modules">' + homeSiteConfig.searchComponentProperties.moduleListText[currentLanguageIndex] + '</td>');
        document.write('<td id="' + tdIDs[1] + '">');
        generateModuleDropDownList(selectorID);
        document.write('</td>');
        document.write('</td>');
    }
}


function generateSourceDropDownList(selectorID) {

    var moduleIterator;
    var currentLanguageIndex = homeSiteConfig.availableLanguages.indexOf(homeSiteConfig.siteLanguage);

    document.write('<select id="' + selectorID + '" multiple>');

    for (moduleIterator in homeSiteConfig.siteSources) {
        document.write('<option value="' + moduleIterator.valueOf() + '">' + homeSiteConfig.siteSources[moduleIterator].displayItem[currentLanguageIndex] + '</option>');
    }

    document.write('</select>');
}
   
function generateSourceCheckedDropDownList(selectorID) {
    generateSourceDropDownList(selectorID);
    var searchQuerySelectorObj = document.getElementById(selectorID);
    if ((homeSiteConfig.siteModulesProperties.isModuleList !== true)) {
        searchQuerySelectorObj.options[homeSiteConfig.siteSourcesProperties.defaultNoSelectionSource].selected = "selected";
    }
    var currentLanguageIndex = homeSiteConfig.availableLanguages.indexOf(homeSiteConfig.siteLanguage);
    var selectorEmptyText = homeSiteConfig.siteSourcesProperties.defaultNoSelectionSourceText[currentLanguageIndex];
    $("#" + selectorID).dropdownchecklist({ emptyText: selectorEmptyText, icon: { placement: 'right', toOpen: 'ui-icon-triangle-1-s', toClose: 'ui-icon-triangle-1-n', width: 150 }, firstItemChecksAll: true });
}

function generateSourceDropDownListWithTD(tdIDs, selectorID) {

    var currentLanguageIndex = homeSiteConfig.availableLanguages.indexOf(homeSiteConfig.siteLanguage);

    document.write('<td id="' + tdIDs[0] + '" title="Help Resources">' + homeSiteConfig.searchComponentProperties.moduleSourceText[currentLanguageIndex] + '</td>');
    document.write('<td id="' + tdIDs[1] + '">');
    generateSourceDropDownList(selectorID);
    document.write('</td>');
    document.write('</td>');
}

function getSearchButtonText(buttonID) {
    var currentLanguageIndex = homeSiteConfig.availableLanguages.indexOf(homeSiteConfig.siteLanguage);
    var buttonObj = document.getElementById(buttonID);

    buttonObj.value = homeSiteConfig.searchComponentProperties.searchButtonValue[currentLanguageIndex];
    // buttonObj.title = homeSiteConfig.searchComponentProperties.searchButtonTooltip[currentLanguageIndex];
}

function generateFooterLinks() {

    var footerLinkIterator;
    var currentLanguageIndex = homeSiteConfig.availableLanguages.indexOf(homeSiteConfig.siteLanguage);
    var firstItemPrinted = false;
    var lastLinkIndex = Object.keys(homeSiteFooterLinks.footerLinks).length - 1;
    var indexLinkCounter = 0;
    
    document.write('<div id="' + homeSiteFooterLinks.divClassID + '">');
    for (footerLinkIterator in homeSiteFooterLinks.footerLinks) {

        if (indexLinkCounter != lastLinkIndex) {
            if (firstItemPrinted == true) {
                document.write(' &#124; ');
            }
            else {
                document.write('<div id="' + homeSiteFooterLinks.divClassID + '_upperLine">');
                firstItemPrinted = true;
            }
        }
        else {
            document.write('</div>');
            document.write('<div id="' + homeSiteFooterLinks.divClassID + '_bottomLine">');
        }
        document.write('<a target="_self" href="' + homeSiteFooterLinks.footerLinks[footerLinkIterator].resourceURL[currentLanguageIndex] + '" ' +
        'class="' + homeSiteFooterLinks.itemsClassID + '">' +
        homeSiteFooterLinks.footerLinks[footerLinkIterator].displayItem[currentLanguageIndex] +
        ' </a>');
        indexLinkCounter++;
    }
    document.write('</div>');
    document.write('</div>');
}

    

// Helper functions for handling search box 

function gcseSQIresetInputText(gcseSearchQueryInput, gcseSQIInitialText, gcseSQIInitialTextColor, statusVector) {
    
    var currentLanguageIndex = homeSiteConfig.availableLanguages.indexOf(homeSiteConfig.siteLanguage);
    
    if (gcseSQIInitialText == '') {
        gcseSearchQueryInput.value = homeSiteConfig.searchComponentProperties.defaultSearchBoxText[currentLanguageIndex];
        gcseSearchQueryInput.style.color = homeSiteConfig.searchComponentProperties.searchBoxUnfocusedColor;
        statusVector['gcseSQIhasDefaultText'] = true;
    }
    else {
        gcseSearchQueryInput.value = gcseSQIInitialText;
        gcseSearchQueryInput.style.color = gcseSQIInitialTextColor;
        statusVector['gcseSQIhasDefaultText'] = false;
    }
    statusVector['gcseSQIhasFocus'] = false;
}

function gcseSQIhandleFocus(gcseSearchQueryInput, statusVector) {
    statusVector['gcseSQIhasFocus'] = true;
    if (statusVector['gcseSQIhasDefaultText'] == true) {
        gcseSearchQueryInput.value = "";
        gcseSearchQueryInput.style.color = '#000000';
        statusVector['gcseSQIhasDefaultText'] = false;
    }
}

function gcseSQIhandleBlur(gcseSearchQueryInput, statusVector) {
    if (statusVector['gcseSQIhasFocus'] == true) {
        if (gcseSearchQueryInput.value == "") {
            gcseSQIresetInputText(gcseSearchQueryInput, '', '', statusVector);
        }
        else {
            statusVector['gcseSQIhasFocus'] = false;
            statusVector['gcseSQIhasDefaultText'] = false;
        }
    }
}
function gcseSQIhandleEnter(e) {
    if (e.keyCode == 13)
        document.getElementById(homeSiteConfig.searchComponentProperties.searchButtonID).click();
}

function gcseSQIhandleNullCheck(gcseSearchQueryInput, statusVector) {
    return ((gcseSearchQueryInput.value != "") && (statusVector['gcseSQIhasDefaultText'] == false));
}