var addIdForAllParagraphs, addTypeForSel, getAllSels, initJsObj, initView, loadParagraphsTypes, onPageLoaded, onerror;

onPageLoaded = function() {
  var error, error1;
  try {
    if (typeof jsObj === "undefined") {
      initJsObj();
    }
    window.mlog = function(msg) {
      return jsObj.log(msg);
    };
    mlog('mlog has initialize...');
    return initView();
  } catch (error1) {
    error = error1;
    alert("JS报错：" + error.stack);
    return initView();
  }
};

addTypeForSel = function(type) {
  var ids;
  ids = [];
  $('.paragraphs.select').each((function(_this) {
    return function(i, e) {
      $(e).attr('class', function(i, cls) {
        return cls.replace(/paragraphsType_-?\d+ /g, '');
      });
      e.classList.add("paragraphsType_" + type);
      ids.push(e.id.split('_')[1]);
      return $(e).removeClass('select');
    };
  })(this));
  ids.join(',');
  return jsObj.setParagraphsType(type, ids);
};

getAllSels = function() {
  var ids;
  ids = [];
  $('.paragraphs.select').each(function(i, e) {
    return ids.push(e.id.split('_')[1]);
  });
  return ids.join(',');
};

initView = function() {
  addIdForAllParagraphs();
  loadParagraphsTypes();
  return $('p').click(function(e) {
    var dt, p;
    console.log("click.....");
    dt = Date.now() - window.lastClickTime;
    window.lastClickTime = Date.now();
    if (dt < 100) {
      return;
    }
    if ($(e.target)[0].tagName === 'P') {
      p = $(e.target);
    } else {
      p = $(e.target).parents('p');
    }
    return $(p).toggleClass("select");
  });
};

addIdForAllParagraphs = function() {
  var c, d;
  d = $('div.container');
  c = d.children();
  return c.each(function(index, tag) {
    tag.id = "paragraphs_" + index;
    return tag.classList.add("paragraphs");
  });
};

loadParagraphsTypes = function() {
  var error, error1, typeStr, types;
  typeStr = jsObj.loadParagraphsTypeStr();
  types = typeStr.split(',');
  try {
    return $('.paragraphs').each((function(_this) {
      return function(i, e) {
        return e.classList.add("paragraphsType_" + types[i]);
      };
    })(this));
  } catch (error1) {
    error = error1;
    mlog("eeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee");
    mlog(error.stack);
    mlog("t size " + types.length);
    mlog("p size " + ($('.paragraphs').length));
    return mlog("EEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEE");
  }
};

onerror = function(e) {
  return alert("JS 发生错误！" + e.stack);
};

initJsObj = function() {
  window.jsObj = {};
  jsObj.log = function(msg) {
    return console.log(msg);
  };
  jsObj.setParagraphsType = function(type, indexStr) {
    return console.log("setParagraphsType type : " + type + " " + indexStr);
  };
  return jsObj.loadParagraphsTypeStr = function() {
    return '1,1,2,2,2,2,2,101,101,207,201,202,202,202,202,201,202,202,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1';
  };
};
