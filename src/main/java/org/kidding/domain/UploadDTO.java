package org.kidding.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

//DB가 아니므로, VO가 아니라 DTO.
@Data
@AllArgsConstructor
public class UploadDTO {

	private String uploadName;
	private String originName;
	private String thumbName;
	private String ext;
	
}
