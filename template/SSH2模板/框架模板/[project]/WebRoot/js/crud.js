var method='add';


$(function(){
	
	$('#grid').datagrid({    
		    url:v_listurl,    
		    columns:v_columns,
			toolbar: [{
				iconCls: 'icon-add',
				text:'添加',
				handler: function(){
					
					$('#editForm').form('clear');
					method='add';
					//写弹出窗口
					$('#editWindow').window("open");
				}
			}],

			pagination:true
			
	});
	
	$('#btnSearch').bind('click',function(){
		//需求：查询表单中的数据
		// 1、得到查询条件，封装成JSON
		var param= getFormData('searchForm');
		
		//2、将参数与传递给后端（ACTION）
		$('#grid').datagrid('load',param);
		
	} );
	
	$('#btnSave').bind('click',function(){
		//需求：查询表单中的数据
		// 1、得到表单数据 ，封装成JSON
		var isValid = $('#editForm').form('validate');
		if(isValid==false)
		{
			return ;
		}		
		var formdata= getFormData('editForm');		
		//2、将数据 与传递给后端（ACTION）
		var v_saveurl="";
		if(method=='update')
		{
			v_saveurl=v_updateurl;
		}
		if(method=='add')
		{
			v_saveurl=v_addurl;
		}		
		$.ajax({
			url:v_saveurl,
			type:'post',
			data:formdata,
			success: function(r)
			{
				//关闭窗口
				$('#editWindow').window("close");
				//刷新表格数据
				$('#grid').datagrid('reload');
			}			
		});	
		
		
	} );
	
	
});


/**
 * 删除数据
 */
function dele(id)
{
	//弹出提示框 ，当用户选择“是” 执行....
	
	$.messager.confirm('提示','是否要删除呢？',
		function(r){
			
			if(r)
			{
				$.ajax({
					url:v_deleteurl+id,	
					success: function (r)
					{			
						//刷新表格数据
						$('#grid').datagrid('reload');
					}
					
				});
			
			}
		
		}
	);
	

}


function update(id)
{
	//弹出修改窗口
	method='update';			
	//弹出修改窗口
	$('#editWindow').window("open");
	//将返回的数据 写到form 里
	$('#editForm').form('load',v_geturl+id);
	
}


