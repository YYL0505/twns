$(document).ready(function(){
  dataToView(disks);
  $('.disk1').mouseenter(function(){
    $('#'+this.id+' > button').removeAttr('disabled');

  })
  .mouseleave(function(){
    $('#'+this.id+' > button').attr('disabled', 'true');
  });

  $('.disk1 > button').click(function(){
    var start=parseInt(this.id.charAt(this.id.length-1));
    disks.splice(start-1, 1);
    dataToView(disks);
  });
});

function dataToView(disks)
{
  divNums=0;
  $('#container').empty();
  var diskDivs=_.map(disks, function(disk){
    return diskDiv(disk);
  });

  _.each(diskDivs, function(div){
    $('#container').append(div);
  });
};

function diskDiv(disk)
{
  divNums++;
  var id="disk"+divNums;
  return $("<div>")
    .attr("class", 'disk1')
    .attr('id', id)
    .append(diskTitle(disk.name))
    .append(diskImg(disk.img))
    .append(diskDesc(disk.desc))
    .append(diskBtn());
};

function diskTitle(title)
{
  return $('<h3>').html(title);
};

function diskImg(src)
{
  return $('<img>').attr('src', src);
};

function diskDesc(desc)
{
  return $('<p>').html(desc);
}

function diskBtn()
{
  var id='btn'+divNums;
  return $('<button>').html('delete')
    .attr('id', id)
    .attr('disabled', 'true');
}

function add()
{
  var newProduct={name:$('#productName').val(),
  img:$('#productImg').val(),
  desc:$('#productDesc').val()};

  disks.push(newProduct);
  dataToView(disks);
}

function search()
{
  var keyword=$('#search').val();
  var searchResult=_.filter(disks, function(disk){
    return disk.name.indexOf(keyword) != -1;
  });
  dataToView(searchResult);
}
