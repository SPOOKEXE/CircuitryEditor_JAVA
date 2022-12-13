package sort;

import main.math.Vector2;

public class Spring2D {

	// Static Fields //
	protected static final int SPRING_ITERATIONS = 8;
	
	// Fields //
	Vector2 Target;
	Vector2 Position;
	Vector2 Velocity;
	
	float Mass;
	float Force;
	float Dampening;
	float Speed;
	
	// Constructor //
	public Spring2D() {
		this.setDefault();
	}
	
	public Spring2D(float mass, float force, float damp, float speed) {
		this.Mass = mass;
		this.Force = force;
		this.Dampening = damp;
		this.Speed = speed;
	}
	
	// Class Methods //
	private void setDefault() {
		this.Target = new Vector2();
		this.Position = new Vector2();
		this.Velocity = new Vector2();
		
		this.Mass = 5;
		this.Force = 50;
		this.Dampening = 4;
		this.Speed = 4;
	}
	
	public void shove(Vector2 force) {
		this.Velocity = this.Velocity.add(force);
	}
	
	public Vector2 update(float deltaTime) {
		float scaledDeltaTime = Math.min(deltaTime, 1) * (this.Speed / SPRING_ITERATIONS);
		for (int i = 1; i < SPRING_ITERATIONS; i++) {
			// local iterationForce = self.Target - self.Position
			Vector2 iterationForce = this.Target.sub(this.Position);
			// local acceleration = (iterationForce * self.Force) / self.Mass
			Vector2 acceleration = iterationForce.mult(this.Force).div(this.Mass);
			// acceleration = acceleration - self.Velocity * self.Damping
			Vector2 dampVelocity = this.Velocity.mult(this.Dampening);
			acceleration = acceleration.sub(dampVelocity);
			// self.Velocity += acceleration * scaledDeltaTime
			acceleration = acceleration.mult(scaledDeltaTime);
			this.Velocity = this.Velocity.add(acceleration);
			// self.Position += self.Velocity * scaledDeltaTime
			Vector2 scaledVelocity = this.Velocity.mult(scaledDeltaTime);
			this.Position = this.Position.add(scaledVelocity);
		}
		return this.Position;
	}
	
}
