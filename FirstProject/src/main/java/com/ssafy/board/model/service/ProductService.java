package com.ssafy.board.model.service;

import java.util.List;

import com.ssafy.board.model.dto.UserSchedule;
import com.ssafy.board.model.dto.Trainer;
import com.ssafy.board.model.dto.UserDetail;

public interface ProductService {

	public void registDetail(UserDetail userDetail);

	public void matchTrainer(String trainerId, String userId);

	public String getTrainerId(String userId);

	public void writeSchedule(UserSchedule schedule);

	public void updateSchedule(UserSchedule schedule);

	public UserSchedule getSchedule(String userId);

	public void deleteSchedule(String userId);

	public UserDetail getDetail(String userId);

	public void modifyDetail(UserDetail userDetail);

}
