function addParentCheck() {
  var text = $.trim($("#aPText").val());
  var sorting = $.trim($("#aPSorting").val());
  if(text.length == 0){
      $("#aPText2").html("请输入分类名称");
      return false;
  }else{
      $("#aPText2").html("");
  }
  if(sorting == 0){
      $("#aPSorting2").html("请输入分类序号");
      return false;
  }else if (isNaN(sorting)){
      $("#aPSorting2").html("请输入正确的分类序号");
      return false;
  }else {
      $("#aPSorting2").html("");
  }
  return true;
}
function editParentCheck() {
    var text = $.trim($("#text").val());
    var sorting = $.trim($("#sorting").val());
    if(text.length == 0){
        $("#text2").html("请输入分类名称");
        return false;
    }else{
        $("#text2").html("");
    }
    if(sorting == 0){
        $("#sorting2").html("请输入分类序号");
        return false;
    }else if (isNaN(sorting)){
        $("#sorting2").html("请输入正确的分类序号");
        return false;
    }else {
        $("#sorting2").html("");
    }
    return true;
}

function addDictionaryCheck() {
    var text = $.trim($("#addText").val());
    var sorting = $.trim($("#addSorting").val());
    if(text.length == 0){
        $("#addText2").html("请输入分类名称");
        return false;
    }else{
        $("#addText2").html("");
    }
    if(sorting == 0){
        $("#addSorting2").html("请输入分类序号");
        return false;
    }else if (isNaN(sorting)){
        $("#addSorting2").html("请输入正确的分类序号");
        return false;
    }else {
        $("#addSorting2").html("");
    }
    return true;
}

function editDictionaryCheck() {
    var text = $.trim($("#editText").val());
    var sorting = $.trim($("#editSorting").val());
    if(text.length == 0){
        $("#editText2").html("请输入分类名称");
        return false;
    }else{
        $("#editText2").html("");
    }
    if(sorting == 0){
        $("#editSorting2").html("请输入分类序号");
        return false;
    }else if (isNaN(sorting)){
        $("#editSorting2").html("请输入正确的分类序号");
        return false;
    }else {
        $("#editSorting2").html("");
    }
    return true;
}

