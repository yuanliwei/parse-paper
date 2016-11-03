format.extend(String.prototype);

$(function() {
//     $( "#draggable" )
  addContent();

  $(".sortable_paper").sortable({
    connectWith: ".sortable_group",
    handle: ".labelcontent"
  });

  $(".sortable_group").sortable({
    connectWith: ".sortable_paper",
    handle: ".labelcontent"
  });

  $(".sortable_question").sortable({
    // connectWith: ".question,section",
    handle: ".labelcontent"
  });

});

function addContent() {
  var template = `<div class="base-content draggable droppable">
                    <div class="headlayout">
                      <ul class="labelcontent">
                        <li>试卷</li>
                        <li>文本</li>
                        <li>组合题</li>
                        <li>单选题</li>
                        <li>多选题</li>
                        <li>填空题</li>
                        <li>判断题</li>
                        <li>问答题</li>
                      </ul>
                    </div>
                    <div class="clearfloat"></div>
                    <div class="contentlayout sortable {0}">
                      <p>{1} - {0}请把我拖拽到目标处！</p>
                    </div>
                  </div>
                `;
  var html = [];
  var tem = 'sortable_paper,sortable_question,sortable_question,sortable_question,sortable_group,sortable_question,sortable_question';
  var tems = tem.split(',');
  tems.forEach(function (item, i) {
    let tem = template.format(item, i);
    html.push(tem);
  });
  for(let i = 0; i<5; i++){
    var tem = template.format(i, i);
    html.push(tem);
  }
  var content = document.getElementById('content');
  content.innerHTML = html.join('');
}
