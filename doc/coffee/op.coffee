onPageLoaded = () ->
  #  app log
  try
    if(typeof(jsObj) == "undefined")
      initJsObj()
    window.mlog = (msg) ->
      jsObj.log(msg)

    mlog('mlog has initialize...')

    initView()
  catch error
    alert("JS报错：#{error.stack}")
    initView()

addTypeForSel = (type) ->
  ids = []
  $('.paragraphs.select').each (i, e) =>
    for index in [0..20]
      e.classList.remove "paragraphsType_#{index}"
    e.classList.add "paragraphsType_#{type}"
    ids.push e.id.split('_')[1]
  ids.join(',')
  jsObj.setParagraphsType(type, ids)

getAllSels = () ->
  ids = []
  $('.paragraphs.select').each (i, e) ->
    ids.push e.id.split('_')[1]
  ids.join(',')

initView = ()->
  addIdForAllParagraphs()
  $('p').click (e) ->
    console.log "click....."
    dt = Date.now() - window.lastClickTime
    window.lastClickTime = Date.now()
    return if(dt < 100)
    if $(e.target)[0].tagName == 'P'
      p = $(e.target)
    else
      p = $(e.target).parents('p')
    # $(p).addClass("select")
    $(p).toggleClass("select")

$(document).ready ()-> onPageLoaded()


# 给所有的段落加上索引id
addIdForAllParagraphs = () ->
  d=$('div.container')
  c=d.children()
  c.each (index, tag) ->
    tag.id = "paragraphs_#{index}"
    tag.classList.add "paragraphs"

onerror = (e) ->
  alert("JS 发生错误！")

initJsObj = () ->
  window.jsObj = {}
  jsObj.log = (msg) ->
    console.log(msg)
  jsObj.setParagraphsType = (type, indexStr) ->
    console.log "setParagraphsType type : #{type} #{indexStr}"
