package com.homestars.ChannelService.model;

import com.homestars.ChannelService.model.BaseResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * 
 * @author nchopra
 *
 */
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChannelResponse<T> extends BaseResponse {
	private T data;
}
