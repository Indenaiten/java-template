package com.codenaiten.template.server.core.feature.user.dto.query;

import com.codenaiten.template.server.core.shared.dto.query.FilterQuery;
import com.codenaiten.template.server.core.shared.dto.query.PageQuery;

public record SearchUserQuery( FilterQuery filterQuery, PageQuery pageQuery ){

}
