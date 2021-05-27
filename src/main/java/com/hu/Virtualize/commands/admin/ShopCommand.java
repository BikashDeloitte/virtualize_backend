package com.hu.Virtualize.commands.admin;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ShopCommand {
    private Long adminId;

    // ShopId will pass when admin want to change the shop details.
    private Long shopId;

    private String shopName;
    private String shopLocation;
}

/* 1
when you add shop in admin list then pass
{
    adminId, ShopName, shopLocation(optional)
}

 */

/* 2
when you want to update the shop details in admin list, then pass
{
    adminId, shopId, shopName(optional), shopLocation(option)
}

*/

/* 3
when you want to delete the user details, then pass this
{
    adminId, shopId
}

*/