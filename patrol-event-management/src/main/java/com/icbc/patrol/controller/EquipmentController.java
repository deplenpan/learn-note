package com.icbc.patrol.controller;

import com.icbc.patrol.model.Equipment;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ：panjiajun
 * @date ：Created on 2019/11/27 8:33
 */
@RestController
@RequestMapping("equipment")
public class EquipmentController {

    @RequestMapping(value = "/query/{equipmentId}", method = RequestMethod.GET)
    @ApiOperation(value = "根据id查询事件单", httpMethod = "GET", produces = "application/json")
    @ApiImplicitParams({@ApiImplicitParam(name = "equipmentId", value = "设备ID", required = true, paramType = "path")})
    public ResponseEntity<Equipment> query(@PathVariable String equipmentId) {
        Equipment equipment = new Equipment();
        equipment.setEquipmentId("");
        equipment.setBrand("");
        equipment.setName("");
        equipment.setCapacity("");
        equipment.setFault("");
        equipment.setLogicalPosition("");
        equipment.setPhysicalPosition("");
        equipment.setType("");
        return new ResponseEntity<>(equipment, HttpStatus.OK);
    }

}
