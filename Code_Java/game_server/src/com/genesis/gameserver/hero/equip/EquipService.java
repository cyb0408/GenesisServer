package com.genesis.gameserver.hero.equip;

import com.genesis.common.core.excelmodel.TempAttrNode3Col;
import com.genesis.common.item.template.ItemTemplate;
import com.genesis.common.prop.battleprop.propeffect.BattlePropEffect;
import com.genesis.common.prop.battleprop.propeffect.PropEffectConverter;
import com.genesis.core.template.TemplateService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 装备属性的全局缓冲<p>
 * 因为目前装备的部分属性是固定的，所以可以做个优化：所有玩家共用一份
 * @author Joey
 *
 */
public class EquipService {

    /**<道具模板ID,固有属性集>*/
    private HashMap<Integer, List<BattlePropEffect>> equipOriginalMap = new HashMap<>();


    public void init(TemplateService templateservice) {
        //装备固有属性缓冲
        Map<Integer, ItemTemplate> templateMap = templateservice.getAll(ItemTemplate.class);
        for (ItemTemplate itemTemplate : templateMap.values()) {
            List<TempAttrNode3Col> attrNodeList = itemTemplate.getAttributeList();

            List<BattlePropEffect> effectList = new ArrayList<>();
            for (TempAttrNode3Col tempAttrNode : attrNodeList) {
                List<BattlePropEffect> tempEffectList =
                        PropEffectConverter.Inst.convertToBattlePropEffect(tempAttrNode);
                effectList.addAll(tempEffectList);
            }
            equipOriginalMap.put(itemTemplate.getId(), effectList);
        }
    }

    public HashMap<Integer, List<BattlePropEffect>> getEquipOriginalMap() {
        return equipOriginalMap;
    }
}
