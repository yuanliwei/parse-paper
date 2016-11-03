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
    # e.classList.remove "paragraphsType_#{index}"
    $(e).attr('class', (i, cls) -> cls.replace(/paragraphsType_-?\d+ /g, ''))
    e.classList.add "paragraphsType_#{type}"
    ids.push e.id.split('_')[1]
    $(e).removeClass('select')
  ids.join(',')
  jsObj.setParagraphsType(type, ids)

getAllSels = () ->
  ids = []
  $('.paragraphs.select').each (i, e) ->
    ids.push e.id.split('_')[1]
  ids.join(',')

initView = ()->
  addIdForAllParagraphs()
  loadParagraphsTypes()
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

# $(document).ready ()-> onPageLoaded()


# 给所有的段落加上索引id
addIdForAllParagraphs = () ->
  d=$('div.container')
  c=d.children()
  c.each (index, tag) ->
    tag.id = "paragraphs_#{index}"
    tag.classList.add "paragraphs"

# 显示页面的时候把已经有类型的段落的颜色加上去
loadParagraphsTypes = () ->
  typeStr = jsObj.loadParagraphsTypeStr()
  types = typeStr.split(',')
  try
    $('.paragraphs').each (i, e) =>
      e.classList.add "paragraphsType_#{types[i]}"
  catch error
    mlog "eeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee"
    mlog error.stack
    mlog "t size #{types.length}"
    mlog "p size #{$('.paragraphs').length}"
    mlog "EEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEE"
  mlog jsObj.loadPaperData()
  # mlog JSON.parse jsObj.loadPaperData()


onerror = (e) ->
  alert("JS 发生错误！"+e.stack)

initJsObj = () ->
  window.jsObj = {}
  jsObj.log = (msg) ->
    console.log(msg)
  jsObj.setParagraphsType = (type, indexStr) ->
    console.log "setParagraphsType type : #{type} #{indexStr}"
  jsObj.loadParagraphsTypeStr = () ->
    '1,1,2,2,2,2,2,101,101,207,201,202,202,202,202,201,202,202,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1';
