package com.homestars.ChannelService.model;

import com.homestars.ChannelService.model.BaseResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.List;

/**
 * 
 * @author nchopra
 *
 */
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChannelListResponse<T> extends BaseResponse {
	private List<T> data;
}
